package team.cnpm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.DTOs.response.ResponseDTOPagination;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTOPagination;
import team.cnpm.exceptions.OwnerNotAvailableException;
import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.SoHoKhauRepository;
import team.cnpm.services.CongDanService;
import team.cnpm.services.SoHoKhauService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SoHoKhauController {
	@Autowired
	private SoHoKhauService soHoKhauService;
	@Autowired
	private SoHoKhauRepository soHoKhauRepo;
	@Autowired
	private CongDanService congDanService;
	
	@GetMapping("/hoKhau")
	public ResponseEntity<ResponseDTOPagination> get(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize){
//		try {
//		HttpHeaders headers = new HttpHeaders();
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
		
			else { pageable =  PageRequest.of(page-1, pageSize);}
			
			
			Page<SoHoKhau> pg =soHoKhauRepo.findAll(pageable);
			List<SoHoKhau> list =pg.getContent();
			
			
			List<SoHoKhauResponseDTO> dtoList = new ArrayList<SoHoKhauResponseDTO>();
			for(SoHoKhau shk : list) dtoList.add(this.soHoKhauService.entityToDTO(shk));
			
//			headers.set("totalElements",String.valueOf(pg.getTotalElements()));
//			headers.set("pageSize", String.valueOf(pg.getSize()));
//			SoHoKhauResponseDTOPagination shkResponse = new 
//					SoHoKhauResponseDTOPagination(dtoList,pg.getTotalElements(),pg.getSize()); 
//			return ResponseEntity.ok()
//					.headers(headers)
//					.body(new ResponseDTO(true,dtoList));
			return ResponseEntity.ok(new ResponseDTOPagination(true, dtoList, pageSize, page, pg.getTotalElements()));
	}
	@GetMapping("/hoKhau/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable(name = "id")String id){
//		try {
			SoHoKhau shk = this.soHoKhauService.getByID(id);
			
			SoHoKhauDetailDTO shkDetail = this.soHoKhauService.entityToDetailDTO(shk);
			
			return ResponseEntity.ok(new ResponseDTO(true,shkDetail));
			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
//					HttpStatus.EXPECTATION_FAILED);
//			// TODO: handle exception
//		}
	}
	@PostMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> post(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO) throws OwnerNotAvailableException{
			CongDan owner = this.congDanService.get(soHoKhauRequestDTO.getOwnerId());
				if(owner.getHoKhauSoHuu() != null) throw new OwnerNotAvailableException();
			SoHoKhau hoKhauCreate = new SoHoKhau(soHoKhauRequestDTO.getAddress());
			hoKhauCreate = this.soHoKhauService.save(hoKhauCreate);
			SoHoKhau hoKhauUpdate = this.soHoKhauService.updateMembers(hoKhauCreate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
//			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdate));
			return ResponseEntity.ok(new ResponseDTO(true,this.soHoKhauService.entityToDetailDTO(hoKhauUpdate)));
	}
	@PutMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> put(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO) throws OwnerNotAvailableException{
		SoHoKhau shk = this.soHoKhauService.getByID(soHoKhauRequestDTO.getId());
		CongDan owner = this.congDanService.get(soHoKhauRequestDTO.getOwnerId());
		if(owner.getHoKhauSoHuu() != null && !shk.getOwner().equals(owner)) throw new OwnerNotAvailableException();
		SoHoKhau hoKhauUpdate = this.soHoKhauService.update(soHoKhauRequestDTO);
		SoHoKhau hoKhauUpdateMembers = this.soHoKhauService.updateMembers(hoKhauUpdate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdateMembers));
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
		
	}
	@GetMapping("/hoKhau/search")
	public ResponseEntity<ResponseDTO> search(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname,
			@RequestParam(name="cccd", required=false) String cccd){
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
		
			else { pageable =  PageRequest.of(page-1, 5);}
			
			
		
		List<SoHoKhau> shkList = this.soHoKhauService.findSHKByName(fname, lname,cccd,pageable);
		
		List<SoHoKhauResponseDTO> dtoList = new ArrayList<SoHoKhauResponseDTO>();
		for(SoHoKhau shk : shkList) dtoList.add(this.soHoKhauService.entityToDTO(shk));
		
		return ResponseEntity.ok(new ResponseDTO(true,dtoList));
	}

@DeleteMapping("/hoKhau/delete/{id}")
public ResponseEntity<ResponseDTO> delete(@PathVariable(name="id") int id){
	String stt = this.soHoKhauService.delete(id);
	if(stt == "Success")
		return ResponseEntity.ok(new ResponseDTO(true, stt));
	else return ResponseEntity.ok(new ResponseDTO(false, stt));
}}