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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import team.cnpm.DTOs.response.ResponseDTOPagination;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.models.CongDan;
import team.cnpm.models.DongGop;
import team.cnpm.models.KhaiBao;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.services.CongDanService;
import team.cnpm.services.KhaiBaoService;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class KhaiBaoController {
	
	@Autowired
	KhaiBaoService khaiBaoService;
	@Autowired
	CongDanService congDanService;
	@Autowired
	CongDanRepository congDanRepo;
	
	long millis=System.currentTimeMillis(); // lấy ngày hiện tại
	Date curdate=new Date(millis);

	@GetMapping("/khaiBaoTamTru")
	public ResponseEntity<Object> getTT(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize){
		try {
			Pageable pageable;
			Sort sort;
				if(sortD==1) {
					 sort = Sort.by(sortBy).descending();
					  pageable =  PageRequest.of(page-1, pageSize,sort);}
				else if(sortD==2) {
					 sort = Sort.by(sortBy).ascending();
					  pageable =  PageRequest.of(page-1, pageSize,sort);}
			
				else { pageable =  PageRequest.of(page-1, pageSize);}
			
			Page<KhaiBao> pg = this.khaiBaoService.getTamTru(pageable);
			
			List<KhaiBao> listTT = pg.getContent();
			
			if(listTT.size() == 0)
				return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy khai báo nào tương ứng,"
						+ " vui lòng kiểm tra lại danh sách"));
			
			return ResponseEntity.ok(new ResponseDTOPagination(true, listTT, pageSize, page, pg.getTotalElements()));
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ResponseDTO(false,"An error occurred!"),	
					HttpStatus.EXPECTATION_FAILED);	
		}
	}
	
	@GetMapping("/khaiBaoTamVang")
	public ResponseEntity<Object> getTV(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize){
		try {
			Pageable pageable;
			Sort sort;
				if(sortD==1) {
					 sort = Sort.by(sortBy).descending();
					  pageable =  PageRequest.of(page-1, pageSize,sort);}
				else if(sortD==2) {
					 sort = Sort.by(sortBy).ascending();
					  pageable =  PageRequest.of(page-1, pageSize,sort);}
			
				else { pageable =  PageRequest.of(page-1, pageSize);}
				
				
				
				Page<KhaiBao> pg = this.khaiBaoService.getTamVang(pageable);
				
				List<KhaiBao> listTV = pg.getContent();
				
				if(listTV.size() == 0)
					return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy khai báo nào tương ứng,"
							+ " vui lòng kiểm tra lại danh sách"));
				
				return ResponseEntity.ok(new ResponseDTOPagination(true, listTV, pageSize, page, pg.getTotalElements()));
			}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new ResponseDTO(false,"An error occurred!"),		
					HttpStatus.EXPECTATION_FAILED);
				
		}
	}
	
	@GetMapping("/khaiBaoTamTru/{id}")
	public ResponseEntity<ResponseDTO> getTTById(@PathVariable(name = "id") int id){
		KhaiBao kbtt = this.khaiBaoService.get(id);
		return ResponseEntity.ok(new ResponseDTO(true,kbtt));
	}
	
	@GetMapping("/khaiBaoTamVang/{id}")
	public ResponseEntity<ResponseDTO> getTVById(@PathVariable(name = "id") int id){
		KhaiBao kbtv = this.khaiBaoService.get(id);
		return ResponseEntity.ok(new ResponseDTO(true,kbtv));
	}
	
	@PostMapping("/khaiBaoTamTru")
	public ResponseEntity<ResponseDTO> postTT(@RequestBody KhaiBao kbtt){
		try {
			
			CongDan congDanTT = this.khaiBaoService.khaiBaoTTToCongDan(kbtt);
			
			// nếu đang trong thgian tạm trú mà KBTT chính người đó thì sẽ lỗi
			if(congDanTT.getCanCuocCongDan() != null && this.congDanRepo.existsByCanCuocCongDan(congDanTT.getCanCuocCongDan()))
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Cong dan da ton tai!"),
						HttpStatus.BAD_REQUEST);
			if(kbtt.getStartTime().compareTo(curdate) > 0 || kbtt.getEndTime().compareTo(curdate)<0) 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Thoi gian khong hop le!"),
						HttpStatus.BAD_REQUEST);
			kbtt.setStatus(congDanTT.getStatus());
			KhaiBao kbttCreate = this.khaiBaoService.save(kbtt);
			CongDan congDanCreate = this.congDanService.save(congDanTT);
			return ResponseEntity.ok(new ResponseDTO(true,kbttCreate));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/khaiBaoTamVang")
	public ResponseEntity<ResponseDTO> postTV(@RequestBody KhaiBao kbtv){
		try {
			
			CongDan congDanTV = this.khaiBaoService.getCDByKhaiBao(kbtv);
			
			if(congDanTV == null) 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Khong tim thay cong dan!"),
					HttpStatus.EXPECTATION_FAILED);
			//không thể khai báo tạm vắng cho người tạm trú hoặc đang tạm vắng
			if(congDanTV.getStatus().equals("Tạm trú") || congDanTV.getStatus().equals("Tạm vắng"))
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Cong dan dang "+congDanTV.getStatus()+"!"),
						HttpStatus.BAD_REQUEST);
			
			if(kbtv.getStartTime().compareTo(curdate) > 0 || kbtv.getEndTime().compareTo(curdate)<0) 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Thoi gian khong hop le!"),
						HttpStatus.BAD_REQUEST);
			kbtv.setStatus("Tạm vắng");
			this.khaiBaoService.khaiBaoTVToCongDan(kbtv);
			KhaiBao kbtvCreate = this.khaiBaoService.save(kbtv);
			return ResponseEntity.ok(new ResponseDTO(true,kbtvCreate));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!"),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/khaiBaoTamTru")
	public ResponseEntity<ResponseDTO> putTT(@RequestBody KhaiBao kbtt){
		try {
			if(!this.congDanRepo.existsByCanCuocCongDan(kbtt.getCanCuocCongDan())) // thời điểm hiện tại đã hết thgian tạm trú
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Cong dan da het thoi gian tam tru!")
						,HttpStatus.EXPECTATION_FAILED);
			
			// thời điểm hiện tại đang trong thời gian tạm trú
			Object kbttUpdate = this.khaiBaoService.updateTamTru_TamVang(kbtt);
			if(kbttUpdate == "CCCD_ERROR") 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Can cuoc cong dan da ton tai!")
					,HttpStatus.EXPECTATION_FAILED);
			if(kbttUpdate == "TIME_ERROR") 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Thoi gian khong hop le!")
					,HttpStatus.EXPECTATION_FAILED);
			CongDan cd = this.khaiBaoService.getCDByKhaiBao(kbtt);
			CongDan cdUpdate = new CongDan(kbtt.getCanCuocCongDan(), kbtt.getPhoneNumber(), kbtt.getFirstName(), kbtt.getLastName(),
				kbtt.getAddress(), kbtt.getDateOfBirth(), kbtt.getGender(), kbtt.getJob(), "Tạm trú");
			cdUpdate.setId(cd.getId());
			System.out.println(cd.getCanCuocCongDan());
			CongDan cdsave = this.congDanRepo.save(cdUpdate);
			return ResponseEntity.ok(new ResponseDTO(true,kbttUpdate));
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
					
		}
	}
	
	@PutMapping("/khaiBaoTamVang")
	public ResponseEntity<ResponseDTO> putTV(@RequestBody KhaiBao kbtv){ //khong cho phép thay đổi công dân hay thông tin của cd
		try {
			CongDan congdanTV = this.khaiBaoService.getCDByKhaiBao(kbtv);
			if(congdanTV == null)
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Cong dan khong ton tai!")
						,HttpStatus.EXPECTATION_FAILED);

			if(!congdanTV.getStatus().equals("Tạm vắng")) 
					return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Cong dan da het thoi gian tam vang!")
							,HttpStatus.EXPECTATION_FAILED);
			
			Object kbtvUpdate = this.khaiBaoService.updateTamTru_TamVang(kbtv);
			if(kbtvUpdate == "TIME_ERROR") 
				return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"Thoi gian khong hop le!")
					,HttpStatus.EXPECTATION_FAILED);
			return ResponseEntity.ok(new ResponseDTO(true,kbtvUpdate));
		} catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(false,"An error occurred!")
					,HttpStatus.EXPECTATION_FAILED);
					
		}
	}
	
	@DeleteMapping("/khaiBaoTamTru/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteTT(@PathVariable(name="id") int id){
		String stt = this.khaiBaoService.deleteTT(id);
		if(stt == "Success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}
	
	@DeleteMapping("/khaiBaoTamVang/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteTV(@PathVariable(name="id") int id){
		String stt = this.khaiBaoService.deleteTV(id);
		if(stt == "Success")
			return ResponseEntity.ok(new ResponseDTO(true, stt));
		else return ResponseEntity.ok(new ResponseDTO(false, stt));
	}
	
	@GetMapping("/khaiBaoTamTru/search")
	public ResponseEntity<Object> findKBTT(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize,
			@RequestParam(name ="cccd", required=false) String cccd, 
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname, 
			@RequestParam(name="phonenumber", required=false) String sdt,
			@RequestParam(name="starttime", required=false) Date startTime,
			@RequestParam(name="endtime", required=false) Date endTime){
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
		
			else { pageable =  PageRequest.of(page-1, pageSize);}
			
			Page<KhaiBao> pg = this.khaiBaoService.findKhaiBao("Tạm trú",cccd, fname, lname, sdt, startTime, endTime, pageable);
			List<KhaiBao> kbtt = pg.getContent();
				
			if(kbtt.size() == 0)
				return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy khai báo nào tương ứng,"
						+ " vui lòng kiểm tra lại danh sách"));
			
			return ResponseEntity.ok(new ResponseDTOPagination(true, kbtt, pageSize,page,pg.getTotalElements()));
	}
	
	@GetMapping("/khaiBaoTamVang/search")
	public ResponseEntity<Object> findKBTV(@RequestParam(name = "sortD", required = false,defaultValue = "3") int sortD,
			@RequestParam(name = "sortBy", required = false ,defaultValue = "id") String sortBy,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "pageSize",required = false, defaultValue = "9") int pageSize,
			@RequestParam(name ="cccd", required=false) String cccd, 
			@RequestParam(name="firstname", required=false) String fname, 
			@RequestParam(name="lastname", required=false) String lname, 
			@RequestParam(name="phonenumber", required=false) String sdt,
			@RequestParam(name="starttime", required=false) Date startTime,
			@RequestParam(name="endtime", required=false) Date endTime){
		Pageable pageable;
		Sort sort;
			if(sortD==1) {
				 sort = Sort.by(sortBy).descending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
			else if(sortD==2) {
				 sort = Sort.by(sortBy).ascending();
				  pageable =  PageRequest.of(page-1, pageSize,sort);}
		
			else { pageable =  PageRequest.of(page-1, pageSize);}
		
		Page<KhaiBao> pg = this.khaiBaoService.findKhaiBao("Tạm vắng",cccd, fname, lname, sdt, startTime, endTime, pageable);
		List<KhaiBao> kbtv = pg.getContent();
			
		if(kbtv.size() == 0)
			return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy khai báo nào tương ứng,"
					+ " vui lòng kiểm tra lại danh sách"));
		
		return ResponseEntity.ok(new ResponseDTOPagination(true, kbtv, pageSize,page,pg.getTotalElements()));
	}
	
	
}
