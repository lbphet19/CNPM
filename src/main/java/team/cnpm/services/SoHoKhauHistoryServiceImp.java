package team.cnpm.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.response.SHKHistoryResponseDTO;
import team.cnpm.models.CongDan;
import team.cnpm.models.DongGop;
import team.cnpm.models.KhaiBao;
import team.cnpm.models.SoHoKhau;
import team.cnpm.models.SoHoKhauHistory;
import team.cnpm.repositories.SoHoKhauHistoryRepository;
import team.cnpm.specifications.KhaiBaoSpecification;
import team.cnpm.specifications.SHKHistorySpecification;

@Service
public class SoHoKhauHistoryServiceImp implements SoHoKhauHistoryService {
	@Autowired
	private SoHoKhauHistoryRepository shkHistoryRepo;
	
	@Override
	public List<SoHoKhauHistory> get() {
		// TODO Auto-generated method stub
		return this.shkHistoryRepo.findAll();
	}

	@Override
	public SoHoKhauHistory save(SoHoKhauHistory shkHistory) {
		// TODO Auto-generated method stub
		return this.shkHistoryRepo.save(shkHistory);
	}

	@Override
	public SoHoKhauHistory update(SoHoKhauHistory shkHistory) {
		// TODO Auto-generated method stub
		SoHoKhauHistory shkHistoryUpdate = this.shkHistoryRepo.findById(shkHistory.getId()).get();
		shkHistoryUpdate.setDescriptions(shkHistory.getDescriptions());
		return this.shkHistoryRepo.save(shkHistory);
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		try {
			this.shkHistoryRepo.deleteById(id);
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Error!";
		}
	}
	
	public SHKHistoryResponseDTO shkHisToShkHisDTO(SoHoKhauHistory shkHis) {
		return new SHKHistoryResponseDTO(shkHis, shkHis.getCongDan(), shkHis.getHoKhau());
	}
	
	public SoHoKhauHistory getByID(int i) {
		return this.shkHistoryRepo.findById(i).get();
	}
	
	public Page<SoHoKhauHistory> findSHKHistory(String cccd, String idHoKhau, Date stime, Date etime, Pageable pageable){
		
		Page<SoHoKhauHistory> page = new PageImpl<SoHoKhauHistory>(new ArrayList<SoHoKhauHistory>());  //declare page
		
		List<String> args= new ArrayList<String>();
		args.add(cccd); args.add(idHoKhau); 
		
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
		
		if( checkAllNull == args.size() && stime == null && etime == null) return page;
		
		//Neu co it nhat 1 arg != null => tiep tuc truy van
		
//		Specification<SoHoKhauHistory> mainSpec = Specification.where(SHKHistorySpecification.cccdLike(args.get(0)))
//				.and(SHKHistorySpecification.idRoiDiLike(args.get(1))).or(SHKHistorySpecification.idChuyenDenLike(args.get(1)));
		Specification<SoHoKhauHistory> mainSpec = Specification.where(SHKHistorySpecification.cccdLike(args.get(0)))
				.and(SHKHistorySpecification.idSHKLike(args.get(1)));
		
		if(stime != null && etime != null) {
			Specification<SoHoKhauHistory> spec1 = mainSpec.and((SHKHistorySpecification.dateLessThanOrEqualTo(etime))
				.and(SHKHistorySpecification.dateGreaterThanOrEqualTo(stime)));
			page= this.shkHistoryRepo.findAll(spec1,pageable);
		}else if(stime == null && etime != null) {
			Specification<SoHoKhauHistory> spec2 = mainSpec.and(SHKHistorySpecification.dateLessThanOrEqualTo(etime));
			page= this.shkHistoryRepo.findAll(spec2,pageable);
		}else if(stime != null && etime == null) {
			Specification<SoHoKhauHistory> spec3 = mainSpec.and(SHKHistorySpecification.dateGreaterThanOrEqualTo(stime));
			page= this.shkHistoryRepo.findAll(spec3,pageable);
		}else page= this.shkHistoryRepo.findAll(mainSpec, pageable);
//		listshkHistory = page.getContent();
		
		return page;	
		
	}

}
