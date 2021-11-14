package team.cnpm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/congDan")
	public ResponseEntity<ResponseDTO> get(){
		try {
			List<CongDan> list = this.congDanService.get();
			return ResponseEntity.ok(new ResponseDTO(true,list));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	

	@GetMapping("/congDan/{id}")
	public ResponseEntity<ResponseDTO> getById(@PathVariable(name = "id") int id){
		try {
			CongDan congDan = this.congDanService.get(id);
			return ResponseEntity.ok(new ResponseDTO(true,congDan));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@PostMapping("/congDan")
	public ResponseEntity<ResponseDTO> post(@RequestBody CongDan congDan){
		try {
			if(this.congDanRepo.existsByCanCuocCongDan(congDan.getCanCuocCongDan()))
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
}
