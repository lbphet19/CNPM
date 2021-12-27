package team.cnpm.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.DTOs.response.ResponseDTOPagination;
import team.cnpm.DTOs.response.SHKHistoryResponseDTO;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.exceptions.OwnerNotAvailableException;
import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.models.SoHoKhauHistory;
import team.cnpm.repositories.SoHoKhauHistoryRepository;
import team.cnpm.repositories.SoHoKhauRepository;
import team.cnpm.services.CongDanService;
import team.cnpm.services.SoHoKhauService;
import team.cnpm.services.SoHoKhauHistoryService;

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
	@Autowired
	private SoHoKhauHistoryRepository soHoKhauHistoryRepo;
	@Autowired
	private SoHoKhauHistoryService soHoKhauHistoryService;
	
	
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
			
			
			Page<SoHoKhau> pg = this.soHoKhauRepo.findAll(pageable);
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
			// thêm vào lịch sử
			this.soHoKhauHistoryRepo.save(new SoHoKhauHistory("Tạo hộ", Date.valueOf(LocalDate.now()),
					"Thêm hộ mới có địa chỉ là "+ hoKhauCreate.getAddress(), null, hoKhauCreate));
			
			SoHoKhau hoKhauUpdate = this.soHoKhauService.updateMembers(hoKhauCreate,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
//			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdate));
			return ResponseEntity.ok(new ResponseDTO(true,this.soHoKhauService.entityToDetailDTO(hoKhauUpdate)));
	}
	@PutMapping("/hoKhau")
	public ResponseEntity<ResponseDTO> put(@RequestBody SoHoKhauRequestDTO soHoKhauRequestDTO) throws OwnerNotAvailableException{
		SoHoKhau shk = this.soHoKhauService.getByID(soHoKhauRequestDTO.getId());
		CongDan owner = this.congDanService.get(soHoKhauRequestDTO.getOwnerId());
		if(owner.getHoKhauSoHuu() != null && (shk.getOwner().getId() != owner.getId())) {
			throw new OwnerNotAvailableException();
		}
		if(!soHoKhauRequestDTO.getAddress().equals(shk.getAddress())) {
			this.soHoKhauHistoryService.save(new SoHoKhauHistory("Chỉnh sửa địa chỉ", Date.valueOf(LocalDate.now()),
					"Địa chỉ " + shk.getAddress() + " được sửa thành "+
					soHoKhauRequestDTO.getAddress(), null, shk));
			shk = this.soHoKhauService.update(soHoKhauRequestDTO);
		}
		SoHoKhau hoKhauUpdateMembers = this.soHoKhauService.updateMembers(shk,
					soHoKhauRequestDTO.getOwnerId(), soHoKhauRequestDTO.getMembers());
			return ResponseEntity.ok(new ResponseDTO(true,hoKhauUpdateMembers));
//			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
//					,HttpStatus.EXPECTATION_FAILED);
		
	}
	@GetMapping("/hoKhau/search")
	public ResponseEntity<Object> search(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize,
			@RequestParam(name="id", required=false) String id,
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname,
			@RequestParam(name="cccd", required=false) String cccd){
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
		
			else { pageable =  PageRequest.of(page-1, pageSize);}
			
		Page<SoHoKhau> pg = this.soHoKhauService.findSHKByName(id, fname, lname, cccd, pageable);	
		List<SoHoKhau> shkList = pg.getContent();
		
		if(shkList.size() == 0)
			return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy hộ khẩu nào tương ứng,"
					+ " vui lòng kiểm tra lại danh sách"));
		
		List<SoHoKhauResponseDTO> dtoList = new ArrayList<SoHoKhauResponseDTO>();
		for(SoHoKhau shk : shkList) dtoList.add(this.soHoKhauService.entityToDTO(shk));
		
		return ResponseEntity.ok(new ResponseDTOPagination(true, dtoList, pageSize,page,pg.getTotalElements()));
	}

	@DeleteMapping("/hoKhau/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(name="id") int id){
		String stt = this.soHoKhauService.delete(id);
		if(stt == "Success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}
	
	/*HISTORY*/
	
	@GetMapping("/shkHistory")
	public ResponseEntity<ResponseDTOPagination> getHistory(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize){

		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
		
			else { pageable =  PageRequest.of(page-1, pageSize);}
			
			
			Page<SoHoKhauHistory> pg = this.soHoKhauHistoryRepo.findAll(pageable);
			List<SoHoKhauHistory> list =pg.getContent();
			List<SHKHistoryResponseDTO> dtoList = new ArrayList<SHKHistoryResponseDTO>();
			for(SoHoKhauHistory shkhis : list) dtoList.add(this.soHoKhauHistoryService.shkHisToShkHisDTO(shkhis));
			
			return ResponseEntity.ok(new ResponseDTOPagination(true, dtoList, pageSize, page, pg.getTotalElements()));
	}
	
	@GetMapping("/shkHistory/{id}")
	public ResponseEntity<ResponseDTO> getOneHistory(@PathVariable(name = "id") int id){

			SoHoKhauHistory shkHis = this.soHoKhauHistoryService.getByID(id);
			
			SHKHistoryResponseDTO shkHisDetail = this.soHoKhauHistoryService.shkHisToShkHisDTO(shkHis);
			
			return ResponseEntity.ok(new ResponseDTO(true,shkHisDetail));
	}
	
	@DeleteMapping("/shkHistory/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteSHKHis(@PathVariable(name="id") int id){
		String stt = this.soHoKhauHistoryService.delete(id);
		if(stt == "Success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}
	
	
	@GetMapping("/shkHistory/search")
	public ResponseEntity<Object> searchSHKHis(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize,
//			@RequestParam(name="firstname", required=false) String fname, 
//			@RequestParam(name="lastname", required=false) String lname,
			@RequestParam(name="cccd", required=false) String cccd,
//			@RequestParam(name="status", required=false) String status,
//			@RequestParam(name="idHKRoiDi", required=false) String idRoiDi,
//			@RequestParam(name="idHKChuyenDen", required=false) String idChuyenDen,
			@RequestParam(name="idHoKhau", required=false) String idHoKhau,
			@RequestParam(name="starttime", required=false) Date stime,
			@RequestParam(name="endtime", required=false) Date etime){
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, 5,sort);}
		
			else { pageable =  PageRequest.of(page-1, 5);}
			
			
		
//		List<SoHoKhauHistory> shkHisList = this.soHoKhauHistoryService.findSHKHistory(cccd, status, fname, lname, idRoiDi, idChuyenDen, stime, etime, pageable);
		Page<SoHoKhauHistory> pg = this.soHoKhauHistoryService.findSHKHistory(cccd, idHoKhau, stime, etime, pageable);
			
		List<SoHoKhauHistory> shkHisList = pg.getContent();
			
		if(shkHisList.size() == 0)
			return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy lịch sử nào tương ứng,"
					+ " vui lòng kiểm tra lại danh sách"));
		
		List<SHKHistoryResponseDTO> dtoList = new ArrayList<SHKHistoryResponseDTO>();
		for(SoHoKhauHistory shkhis : shkHisList) dtoList.add(this.soHoKhauHistoryService.shkHisToShkHisDTO(shkhis));
		
		return ResponseEntity.ok(new ResponseDTOPagination(true, dtoList, pageSize,page,pg.getTotalElements()));
	}
	
	
	
	
	
	
	
	
	
}