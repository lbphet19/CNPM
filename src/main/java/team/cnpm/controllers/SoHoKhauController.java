package team.cnpm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.SoHoKhauRepository;
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
	@GetMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> get(){
//		try {
			List<SoHoKhau> list = this.soHoKhauService.get();
			
			List<SoHoKhauResponseDTO> dtoList = new ArrayList<SoHoKhauResponseDTO>();
			for(SoHoKhau shk : list) dtoList.add(this.soHoKhauService.entityToDTO(shk));
			
			return ResponseEntity.ok(new ResponseDTO(true,dtoList));
			
//			return ResponseEntity.ok(new ResponseDTO(true,list));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
//			// TODO: handle exception
//		}
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
	public ResponseEntity<ResponseDTO> post(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO){
		try {
			SoHoKhau hoKhauCreate = new SoHoKhau(soHoKhauRequestDTO.getAddress());
			hoKhauCreate = this.soHoKhauService.save(hoKhauCreate);
			SoHoKhau hoKhauUpdate = this.soHoKhauService.updateMembers(hoKhauCreate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
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
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdateMembers));
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
		
	}
	@GetMapping("/hoKhau/search")
	public ResponseEntity<ResponseDTO> search(
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname){
		List<SoHoKhau> shkList = this.soHoKhauService.findSHKByName(fname, lname);
		
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