package team.cnpm.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.cnpm.models.CongDan;
import team.cnpm.models.ERole;
import team.cnpm.models.KhaiBao;
import team.cnpm.models.Role;
import team.cnpm.models.User;
import team.cnpm.payload.request.LoginRequest;
import team.cnpm.payload.request.SignupRequest;
import team.cnpm.payload.response.JwtResponse;
import team.cnpm.payload.response.MessageResponse;
import team.cnpm.security.jwt.JwtUtils;
import team.cnpm.security.services.UserDetailsImpl;
import team.cnpm.specifications.KhaiBaoSpecification;
import team.cnpm.repositories.UserRepository;
import team.cnpm.repositories.RoleRepository;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.repositories.KhaiBaoRepository;
import team.cnpm.services.KhaiBaoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController { 
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	KhaiBaoRepository khaiBaoRepo;
	
	@Autowired
	KhaiBaoService khaiBaoService;
	
	@Autowired
	CongDanRepository congDanRepo;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		/*Refresh lại List mỗi khi sign in*/
		long millis=System.currentTimeMillis(); // lấy ngày hiện tại
		Date curdate=new Date(millis);
		
		//refresh Tạm trú
		List<KhaiBao> listKBTT = this.khaiBaoRepo.findAll(Specification.where(KhaiBaoSpecification.sttLike("Tạm trú"))
				.and(KhaiBaoSpecification.EtimeLessThan(curdate)));
		
		List<CongDan> cdTT = new ArrayList<CongDan>();
		for(KhaiBao i : listKBTT) {
			CongDan cd = this.khaiBaoService.getCDByKhaiBao(i);
			if(cd != null && !cdTT.contains(cd)) cdTT.add(cd);
		}
		if(cdTT.size() != 0) this.congDanRepo.deleteAll(cdTT);  // xóa khỏi list nếu quá thời hạn tạm trú
		
		//refresh Tạm vắng
		List<KhaiBao> listKBTV = this.khaiBaoRepo.findAll(Specification.where(KhaiBaoSpecification.sttLike("Tạm vắng"))
				.and(KhaiBaoSpecification.EtimeLessThan(curdate)));
		
		List<CongDan> cdTV = new ArrayList<CongDan>();
		for(KhaiBao i : listKBTV) {
			CongDan cd = this.khaiBaoService.getCDByKhaiBao(i);
			if(cd != null && !cdTV.contains(cd)) cdTV.add(cd);
		}
		if(cdTV.size() != 0) {
			for(CongDan i: cdTV) i.setStatus("Đang ở");  // đặt về trạng thái đang ở nếu quá thời hạn
			this.congDanRepo.saveAll(cdTV);
		}
		/*End refresh*/
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
