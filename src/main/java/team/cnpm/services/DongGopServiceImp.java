package team.cnpm.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.response.DongGopDetailsDTO;
import team.cnpm.DTOs.response.HKDongGopDTO;
import team.cnpm.DTOs.response.ListDongGopDTO;
import team.cnpm.models.DongGop;
import team.cnpm.models.HoKhauDongGop;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.DongGopRepository;
import team.cnpm.specifications.DongGopSpecification;

@Service
public class DongGopServiceImp implements DongGopService {
	@Autowired
	private DongGopRepository dongGopRepo;
	
	
	@Autowired
	private HoKhauDongGopService HKDGService;
	@Autowired
	private SoHoKhauService SHKService;
	
	public Page<DongGop> findAll(Pageable pageable) {
		
		return this.dongGopRepo.findAll(pageable);
	}
	@Override
	public DongGop getbyid(int id){
		return this.dongGopRepo.getById(id);
	}
	public List<DongGop> get(){
		return this.dongGopRepo.findAll();
	}
	
	public DongGop save(DongGop dongGop) {
		return this.dongGopRepo.save(dongGop);
	}
	public DongGop update(DongGop dongGopUpdate) {
		DongGop dongGop = this.dongGopRepo.findById(dongGopUpdate.getId()).get();
		dongGop.setDate(dongGopUpdate.getDate());
		dongGop.setDescriptions(dongGopUpdate.getDescriptions());
		dongGop.setEventName(dongGopUpdate.getEventName());
		return this.dongGopRepo.save(dongGop);
	}

	
	public ListDongGopDTO entityToDTO(DongGop dg) {
		return new ListDongGopDTO(dg.getId(),dg.getEventName(),dg.getDate(),dg.getDescriptions()
				,dg.getListHoKhauDongGop(),dg.getMucphi());
	}
	
	
	@Override
	public DongGopDetailsDTO entityToDetailsDTOroi(DongGop dg1) {
		
		List<SoHoKhau> allSHK = this.SHKService.get();   // lấy tổng tất cả hộ khẩu để lọc
		List<HKDongGopDTO> listHKchuaDGdto = new ArrayList<HKDongGopDTO>();  //những hộ chưa đóng góp
		
		// convert từ list HKDG sang list HKDG_dto
		List<HKDongGopDTO> listHKDGdto = new ArrayList<HKDongGopDTO>();
		for(HoKhauDongGop i : dg1.getListHoKhauDongGop()) {
			listHKDGdto.add(this.HKDGService.HKDongGopToHKDongGopDTO(i));  // convert
			
			if(allSHK.contains(i.getSoHoKhau())) allSHK.remove(i.getSoHoKhau());  // loại ra những hộ khẩu đã đóng
		}
		
		for(SoHoKhau i : allSHK) listHKchuaDGdto.add(new HKDongGopDTO(i.getId(),
				i.getOwner().getFirstName()+" "+i.getOwner().getLastName(),
				i.getAddress(),
				i.getOwner().getPhoneNumber(),
				0));  // convert những hộ chưa đóng
		
		return new DongGopDetailsDTO(dg1.getId(), dg1.getEventName(),dg1.getDate(),dg1.getDescriptions(), listHKDGdto, dg1.getMucphi());
	}
	@Override
	public DongGopDetailsDTO entityToDetailsDTOchua(DongGop dg1) {
		
		List<SoHoKhau> allSHK = this.SHKService.get();   // lấy tổng tất cả hộ khẩu để lọc
		List<HKDongGopDTO> listHKchuaDGdto = new ArrayList<HKDongGopDTO>();  //những hộ chưa đóng góp
		
		// convert từ list HKDG sang list HKDG_dto
		List<HKDongGopDTO> listHKDGdto = new ArrayList<HKDongGopDTO>();
		for(HoKhauDongGop i : dg1.getListHoKhauDongGop()) {
			listHKDGdto.add(this.HKDGService.HKDongGopToHKDongGopDTO(i));  // convert
			
			if(allSHK.contains(i.getSoHoKhau())) allSHK.remove(i.getSoHoKhau());  // loại ra những hộ khẩu đã đóng
		}
		

		for(SoHoKhau i : allSHK) listHKchuaDGdto.add(new HKDongGopDTO(i.getId(),
				i.getOwner().getFirstName()+" "+i.getOwner().getLastName(),
				i.getAddress(),
				i.getOwner().getPhoneNumber(),
				0));  // convert những hộ chưa đóng  
		
		return new DongGopDetailsDTO(dg1.getId(), dg1.getEventName(),dg1.getDate(),dg1.getDescriptions(), listHKchuaDGdto, dg1.getMucphi());
	}
	
	@Override
	public List<DongGop> findEvent(String name, Date date,Pageable pageable){
		List<DongGop> listDG = new ArrayList<DongGop>();
		
		// chuẩn chỉ hóa tên để search cho đúng
		if(name!=null)
			if(name.isBlank()) name = null;  
			else name = name.trim();
		
		if(name==null && date==null) return listDG;   // cả 2 null sẽ return list rỗng
		
		Specification<DongGop> spec = Specification.where(DongGopSpecification.dateLike(date)).and(DongGopSpecification.eventNameLike(name));
		Page<DongGop> page= this.dongGopRepo.findAll(spec,pageable);
		listDG=page.getContent();
		return listDG;
	}
	
	
	public String delete(int id) {
		try {
			this.dongGopRepo.deleteById(id);
			return "Success";
		} catch (Exception e) {
			return "Error!";
			// TODO: handle exception
		}
	}

	

}
