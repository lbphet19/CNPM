package team.cnpm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.SoHoKhauRepository;
import team.cnpm.services.SoHoKhauService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SoHoKhauController {
	@Autowired
	private SoHoKhauService soHoKhauService;
	@Autowired
	private SoHoKhauRepository soHoKhauRepo;
	@GetMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> get(){
		try {
			List<SoHoKhau> list = this.soHoKhauService.get();
			return ResponseEntity.ok(new ResponseDTO(true,list));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@PostMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> post(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO){
		try {
			SoHoKhau hoKhauCreate = new SoHoKhau(soHoKhauRequestDTO.getAddress());
			hoKhauCreate = this.soHoKhauService.save(hoKhauCreate);
			SoHoKhau hoKhauUpdate = this.soHoKhauService.updateMembers(hoKhauCreate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembersId());
			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdate));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@PutMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> put(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO){
		SoHoKhau hoKhauUpdate = this.soHoKhauService.update(soHoKhauRequestDTO);
		SoHoKhau hoKhauUpdateMembers = this.soHoKhauService.updateMembers(hoKhauUpdate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembersId());
			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdateMembers));
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
		
	}
}
