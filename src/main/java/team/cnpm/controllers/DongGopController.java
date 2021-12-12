package team.cnpm.controllers;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.cnpm.DTOs.response.DongGopDetailsDTO;
import team.cnpm.DTOs.response.HKDongGopDTO;
import team.cnpm.DTOs.response.ListDongGopDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.models.DongGop;
import team.cnpm.models.HoKhauDongGop;
import team.cnpm.repositories.DongGopRepository;
import team.cnpm.services.DongGopService;
import team.cnpm.services.HoKhauDongGopService;

@Controller
@RequestMapping("/api")
public class DongGopController {
	@Autowired
	private DongGopService dongGopService;
	
	@Autowired
	private DongGopRepository dongGopRepo;
	
	@Autowired
	private HoKhauDongGopService HKDGService;
	
	@GetMapping("/dongGop")
	public ResponseEntity<ResponseDTO> getpage(@RequestParam(name = "sortD", required = false) int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false) int page){
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
		
		
		Page<DongGop> pg =dongGopService.findAll(pageable);
		List<DongGop> list =pg.getContent();
		List<ListDongGopDTO> listDTO = new ArrayList<ListDongGopDTO>();
		for(DongGop c : list) listDTO.add(this.dongGopService.entityToDTO(c));
		return ResponseEntity.ok(new ResponseDTO(true, listDTO)); 
		}
	catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
				HttpStatus.EXPECTATION_FAILED);
		
	}}
	
	@GetMapping("/dongGop/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable(name = "id") int id){
		try {
			DongGop dg = this.dongGopService.getbyid(id);
			
			DongGopDetailsDTO dgDetail = this.dongGopService.entityToDetailsDTO(dg);
			return ResponseEntity.ok(new ResponseDTO(true,dgDetail));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@PostMapping("/dongGop")
	public ResponseEntity<ResponseDTO> post(@RequestBody DongGop dongGop){
		try {
			
			DongGop dongGopCreate = this.dongGopService.save(dongGop);
			return ResponseEntity.ok(new ResponseDTO(true,dongGopCreate));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	@PutMapping("/dongGop")
	public ResponseEntity<ResponseDTO> put(@RequestBody DongGop dg){
		try {
			DongGop dgupdate = this.dongGopService.update(dg);
			if(dgupdate == null) 
				System.out.println("Null");
//				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
			return ResponseEntity.ok(new ResponseDTO(true,dgupdate));
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
					
		}
	}
	
	@GetMapping("/dongGop/search")
	public ResponseEntity<ResponseDTO> search(@RequestParam(name="name", required=false) String name, 
			@RequestParam(name="date", required=false) Date date){
		List<DongGop> listDG = this.dongGopService.findEvent(name, date);
		
		List<ListDongGopDTO> listDTO = new ArrayList<ListDongGopDTO>();
		for(DongGop c : listDG) listDTO.add(this.dongGopService.entityToDTO(c));
		return ResponseEntity.ok(new ResponseDTO(true, listDTO)); 
	}
	@DeleteMapping("/dongGop/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(name="id") int id){
		String stt = this.dongGopService.delete(id);
		if(stt == "success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}

	
	
	/*************/   
	@GetMapping("/HKdongGop/{id}")
	public ResponseEntity<ResponseDTO> getHKDGById(@PathVariable(name = "id") int id){
		try {
			HoKhauDongGop hkdg = this.HKDGService.getbyid(id);
			
			HKDongGopDTO hkdgDetail = this.HKDGService.HKDongGopToHKDongGopDTO(hkdg);
			return ResponseEntity.ok(new ResponseDTO(true,hkdgDetail));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@PostMapping("/HKdongGop/add")
	public ResponseEntity<ResponseDTO> addHK(@RequestBody HoKhauDongGop hkdg){  // cần tạo một HKDGRequestDTO
		try {
			
			HoKhauDongGop hkdgCreate = this.HKDGService.save(hkdg);
			return ResponseEntity.ok(new ResponseDTO(true,hkdgCreate));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	  /***********/
	@PutMapping("/HKdongGop/update")
	public ResponseEntity<ResponseDTO> updateHKDG(@RequestBody HoKhauDongGop dg){
		try {
			HoKhauDongGop hkdgUpdate = this.HKDGService.update(dg);
//			if(hkdgUpdate == null) 
//				System.out.println("Null");
//				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
			return ResponseEntity.ok(new ResponseDTO(true,hkdgUpdate));
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
					
		}
	}
	
	//deletemapping
	@DeleteMapping("/HKdongGop/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteHKDG(@PathVariable(name="id") int id){
		String stt = this.HKDGService.delete(id);
		if(stt == "success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}


}


