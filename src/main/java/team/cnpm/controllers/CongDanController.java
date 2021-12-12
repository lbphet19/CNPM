package team.cnpm.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.cnpm.DTOs.response.CongDanDetailDTO;
import team.cnpm.DTOs.response.CongDanResponseDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.models.CongDan;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.services.CongDanService;

@Controller
@RequestMapping("/api")
public class CongDanController {
	@Autowired
	private CongDanService congDanService;
	
	@Autowired
	private CongDanRepository congDanRepo;
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/congDan")
	
	public ResponseEntity<ResponseDTO> getpage(@RequestParam(name = "sortD", required = false, defaultValue = "1" ) Integer sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "address") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page){
		try {
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
		
			else { pageable =  PageRequest.of(page-1, 5);}
			
			
			Page<CongDan> pg =congDanService.findAll(pageable);
			List<CongDan> list =pg.getContent();
			List<CongDanResponseDTO> listDTO = new ArrayList<CongDanResponseDTO>();
		for(CongDan c : list) listDTO.add(this.congDanService.entityToDTO(c));
			return ResponseEntity.ok(new ResponseDTO(true, listDTO)); 
			}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
			
		}

	}
	
//	@GetMapping("/congDan")
//	public ResponseEntity<ResponseDTO> get(){
//		List<CongDan> list = this.congDanService.get();
//		
//		List<CongDanResponseDTO> listDTO = new ArrayList<CongDanResponseDTO>();
//		for(CongDan c : list) listDTO.add(this.congDanService.entityToDTO(c));
//		
//		return ResponseEntity.ok(new ResponseDTO(true, listDTO));
//	}
//	

	@GetMapping("/congDan/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable(name = "id") int id){
			CongDan congDan = this.congDanService.get(id);
			CongDanDetailDTO cdDetail = this.congDanService.entityToDetailDTO(congDan);
			return ResponseEntity.ok(new ResponseDTO(true,cdDetail));
	}
	@PostMapping("/congDan")
	public ResponseEntity<ResponseDTO> post(@RequestBody CongDan congDan){
		try {
			if(congDan.getCanCuocCongDan() != null && this.congDanRepo.existsByCanCuocCongDan(congDan.getCanCuocCongDan()))
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Can cuoc cong dan da ton tai!"),
						HttpStatus.BAD_REQUEST);
			CongDan congDanCreate = this.congDanService.save(congDan);
			return ResponseEntity.ok(new ResponseDTO(true,congDanCreate));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	@PutMapping("/congDan")
	public ResponseEntity<ResponseDTO> put(@RequestBody CongDan congDan){
		try {
			CongDan congDanUpdate = this.congDanService.update(congDan);
			if(congDanUpdate == null) 
				System.out.println("Null");
//				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
			return ResponseEntity.ok(new ResponseDTO(true,congDanUpdate));
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
					
		}
	}
	
	@DeleteMapping("/congDan/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(name="id") int id){
		String stt = this.congDanService.delete(id);
		if(stt == "success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}

//	@GetMapping("/congDan/search")
//	public ResponseEntity<ResponseDTO> findForAddingSHK(@RequestParam(name ="cccd", required=false) String cccd, 
//			@RequestParam(name="firstname", required=false) String fname, 
//			@RequestParam(name="lastname", required=false) String lname, 
//			@RequestParam(name="phonenumber", required=false) String sdt){
//
//				List<CongDan> cd = this.congDanService.findCongDan(cccd, fname, lname, sdt);
//							
//				List<CongDanResponseDTO> cdDTO= new ArrayList<CongDanResponseDTO>();
//				for(CongDan c : cd) cdDTO.add(this.congDanService.entityToDTO(c));
//				
//				return ResponseEntity.ok(new ResponseDTO(true, cdDTO));
//	}
//}
	@GetMapping("/congDan/search")
	public ResponseEntity<ResponseDTO> search(@RequestParam(name ="cccd", required=false) String cccd, 
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname, 
			@RequestParam(name="phonenumber", required=false) String sdt,
			@RequestParam(name = "sortD", required = false) int sortD,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "page", required = false) int page){
		Pageable pageable;
		if(sortD==1) {
			  pageable =  PageRequest.of(page-1, 3, Direction.DESC, sortBy);}
		else if(sortD==2) {
			  pageable =   PageRequest.of(page-1, 3, Direction.ASC, sortBy);}
		else { pageable =  PageRequest.of(page-1, 3);}
				List<CongDan> cd = this.congDanService.findCongDan(cccd, fname, lname, sdt,pageable);
							
				List<CongDanResponseDTO> cdDTO= new ArrayList<CongDanResponseDTO>();
				for(CongDan c : cd) cdDTO.add(this.congDanService.entityToDTO(c));
				
				return ResponseEntity.ok(new ResponseDTO(true, cdDTO));
	}
}

