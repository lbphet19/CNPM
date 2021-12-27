package team.cnpm.services;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.request.CongDanOfSHKRequestDTO;
import team.cnpm.DTOs.request.CongDanRequestDTO;
import team.cnpm.DTOs.response.CongDanDetailDTO;
import team.cnpm.DTOs.response.CongDanOfSHK_DTO;
import team.cnpm.DTOs.response.CongDanResponseDTO;
import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.models.SoHoKhauHistory;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.repositories.SoHoKhauRepository;
import team.cnpm.specifications.CongDanSpecification;

@Service
public class CongDanServiceImp implements CongDanService {
	@Autowired
	private CongDanRepository congDanRepo;
	@Autowired
	private SoHoKhauRepository shkRepo;
	@Autowired
	private SoHoKhauHistoryService shkHistoryService;
	public List<CongDan> get(){
		return this.congDanRepo.findAll();
	}
	
	public Page<CongDan> findAll(Pageable pageable) {
		return this.congDanRepo.findAll(pageable);
	}
	public CongDan get(int id) {
		return this.congDanRepo.findById(id).get();
	}
	public CongDan save(CongDan congDan) {
		return this.congDanRepo.save(congDan);
	}
	public CongDan save(CongDanRequestDTO congDanDTO) {
		CongDan congDanCreate = this.RequestDTOToEntity(congDanDTO);
		if(congDanDTO.getIdSHK() != null && congDanDTO.getIdSHK().length() > 0) {
			SoHoKhau shk = this.shkRepo.findById(congDanDTO.getIdSHK()).get();
			congDanCreate.setHoKhau(shk);
			congDanCreate = this.congDanRepo.save(congDanCreate);
			this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
					"Công dân " + congDanCreate.getFirstName() + " " + congDanCreate.getLastName() + " chuyển đến"
					, congDanCreate, shk));
		}
		else {
			congDanCreate = congDanRepo.save(congDanCreate);
		}
		return congDanCreate;
			
	}
	public CongDan update(CongDan congDanUpdate) {
		CongDan congDan = this.congDanRepo.findById(congDanUpdate.getId()).get();
		if(!congDanUpdate.getCanCuocCongDan().equals(congDan.getCanCuocCongDan()) && this.congDanRepo.existsByCanCuocCongDan(congDanUpdate.getCanCuocCongDan()))
			return null;
		congDan.setAddress(congDanUpdate.getAddress());
		congDan.setCanCuocCongDan(congDanUpdate.getCanCuocCongDan());
		congDan.setDateOfBirth(congDanUpdate.getDateOfBirth());
		congDan.setDepartmentTime(congDanUpdate.getDepartmentTime());
		congDan.setFirstName(congDanUpdate.getFirstName());
		congDan.setLastName(congDanUpdate.getLastName());
		congDan.setGender(congDanUpdate.getGender());
		congDan.setImage(congDanUpdate.getImage());
		congDan.setJob(congDanUpdate.getJob());
		congDan.setPhoneNumber(congDanUpdate.getPhoneNumber());
		congDan.setRelationship(congDanUpdate.getRelationship());
		congDan.setSpecialNotes(congDanUpdate.getSpecialNotes());
		congDan.setStatus(congDanUpdate.getStatus());
		return this.congDanRepo.save(congDanUpdate);
	}
	public String delete(int id) {
		try {
		this.congDanRepo.deleteById(id);
		return "Success";
		}catch (Exception e) {
			return "Error";
			// TODO: handle exception
		}
	}
	public CongDan RequestDTOToEntity(CongDanRequestDTO dto) {
		return new CongDan(dto.getCanCuocCongDan(),dto.getPhoneNumber(),dto.getFirstName()
				,dto.getLastName(),dto.getAddress(),dto.getDateOfBirth(),dto.getGender(),
				dto.getJob(),dto.getSpecialNotes(),dto.getStatus(),dto.getDepartmentTime(),
				dto.getRelationship(),dto.getImage());
	}

	public CongDanResponseDTO entityToDTO(CongDan cd) {
//		return new CongDanResponseDTO(cd.getId(),cd.getCanCuocCongDan(), cd.getPhoneNumber(), cd.getFirstName(), 
//				cd.getLastName(), cd.getAddress(), cd.getDateOfBirth(), cd.getGender(), cd.getJob(), 
//				cd.getImage(), cd.getHoKhau()==null ? null : cd.getHoKhau().getId());
		return new CongDanResponseDTO(cd.getId(),cd.getCanCuocCongDan(), cd.getPhoneNumber(), cd.getFirstName(), 
				cd.getLastName(), cd.getHoKhau() == null? null: cd.getHoKhau().getAddress(), cd.getDateOfBirth(), cd.getGender(), cd.getJob(), 
				cd.getImage(), cd.getHoKhau()==null ? null : cd.getHoKhau().getId());
	}
	
	public CongDanDetailDTO entityToDetailDTO(CongDan cd) {
		return new CongDanDetailDTO(cd.getId(), cd.getCanCuocCongDan(), cd.getPhoneNumber(), cd.getFirstName(),
				cd.getLastName(), cd.getHoKhau() == null ? null : cd.getHoKhau().getAddress(), cd.getDateOfBirth(), cd.getGender(), cd.getJob(), 
				cd.getSpecialNotes(), cd.getStatus(), cd.getDepartmentTime(), cd.getRelationship(), cd.getImage(),
				cd.getHoKhauSoHuu() == null ? null : cd.getHoKhauSoHuu().getId(), 
				cd.getHoKhau() == null ? null : cd.getHoKhau().getId());
	}
	
	public CongDanOfSHK_DTO entityToCDofSHK_DTO(CongDan cd) {
		return new CongDanOfSHK_DTO(cd.getId(), cd.getCanCuocCongDan(), cd.getFirstName(), cd.getLastName(), 
				cd.getAddress(), cd.getDateOfBirth(), cd.getPhoneNumber(), cd.getGender(), cd.getJob(),  cd.getRelationship(),
				cd.getSpecialNotes(), cd.getStatus(), cd.getImage());
	}
	
	
	
	public Page<CongDan> findCongDan(String cccd, String fname, String lname, String sdt,Pageable pageable){
//		List<CongDan> cdan = new ArrayList<CongDan>();
		Page<CongDan> page = new PageImpl<CongDan>(new ArrayList<CongDan>());  // declare page
		
		List<String> args= new ArrayList<String>();
		args.add(cccd); args.add(fname); args.add(lname); args.add(sdt);
		
		//Kiem tra neu all args = null => return cdan (empty list) 
		int checkAllNull= 0;
		for(int i=0; i< args.size(); i++)
			if(args.get(i)!=null)
				if(args.get(i).isBlank()) {
					args.set(i, null);
					checkAllNull++;
				}
				else args.set(i, args.get(i).trim());
			else checkAllNull++;
		
		if( checkAllNull == args.size() ) return page;
		
		//Neu co it nhat 1 arg != null => tiep tuc truy van
		Specification<CongDan> spec = Specification
				.where(CongDanSpecification.cccdLike(args.get(0))).and(CongDanSpecification.fNameLike(args.get(1)))
				.and(CongDanSpecification.lNameLike(args.get(2))).and(CongDanSpecification.sdtLike(args.get(3)));
		
		
		page= this.congDanRepo.findAll(spec,pageable);
//		cdan =page.getContent();
		return page;
	}

	@Override
	public CongDan updateRelationship(CongDan congDan) {
		// TODO Auto-generated method stub
		CongDan congDanUpdate = this.congDanRepo.findById(congDan.getId()).get();
		congDanUpdate.setRelationship(congDan.getRelationship());
		return this.congDanRepo.save(congDanUpdate);
	}

	@Override
	public CongDan updateRelationship(CongDanOfSHKRequestDTO congDan) {
		// TODO Auto-generated method stub
		CongDan congDanUpdate = this.congDanRepo.findById(congDan.getId()).get();
		congDanUpdate.setRelationship(congDan.getRelationship());
		return this.congDanRepo.save(congDanUpdate);
	}

}
