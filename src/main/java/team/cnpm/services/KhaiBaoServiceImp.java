package team.cnpm.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.response.ResponseDTO;
import team.cnpm.models.CongDan;
import team.cnpm.models.KhaiBao;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.repositories.KhaiBaoRepository;
import team.cnpm.specifications.CongDanSpecification;
import team.cnpm.specifications.KhaiBaoSpecification;

@Service
public class KhaiBaoServiceImp implements KhaiBaoService{
	
	@Autowired
	KhaiBaoRepository khaiBaoRepo;
	@Autowired
	CongDanService congDanservice;
	@Autowired
	CongDanRepository congDanRepo;
	
	public Page<KhaiBao> getTamTru(Pageable pageable){
		return this.khaiBaoRepo.findAll(Specification.where(KhaiBaoSpecification.sttLike("Tạm trú")), pageable);
	}
	
	public Page<KhaiBao> getTamVang(Pageable pageable){
		return this.khaiBaoRepo.findAll(Specification.where(KhaiBaoSpecification.sttLike("Tạm vắng")), pageable);
	}
	
	public KhaiBao get(int id) {
		return this.khaiBaoRepo.getById(id);
	}
	
	public KhaiBao save(KhaiBao kb) {
		return this.khaiBaoRepo.save(kb);
	}
	
	public CongDan khaiBaoTTToCongDan(KhaiBao kb) {
		return new CongDan(kb.getCanCuocCongDan(), kb.getPhoneNumber(), kb.getFirstName(), kb.getLastName(),
				kb.getAddress(), kb.getDateOfBirth(), kb.getGender(), kb.getJob(), "Tạm trú");
	}
	
	public CongDan khaiBaoTVToCongDan(KhaiBao kb) {
		CongDan cdUpdate = this.getCDByKhaiBao(kb);
		
		if(cdUpdate == null) return null;
		
		cdUpdate.setStatus("Tạm vắng");
		CongDan lastUpdate = this.congDanservice.save(cdUpdate);
		return lastUpdate;
	}
	
	public CongDan getCDByKhaiBao(KhaiBao kb) {
		CongDan cd = this.congDanRepo.getByCCCD(kb.getCanCuocCongDan());
		if(cd == null) return null; // nếu không tìm được ai=> return null
		return cd;
	}
	
	public Object updateTamTru_TamVang(KhaiBao kbNew) {
		KhaiBao kb = this.khaiBaoRepo.getById(kbNew.getId());
		
		long millis=System.currentTimeMillis(); // lấy ngày hiện tại
		Date curdate=new Date(millis);
		
		//kiểm tra xem có sửa thành cccd đã có trong list cong dan hay không
		if(!kbNew.getCanCuocCongDan().equals(kb.getCanCuocCongDan()) && this.congDanRepo.existsByCanCuocCongDan(kbNew.getCanCuocCongDan()))
			return "CCCD_ERROR";
		// nếu startTime trước sau ngày hiện tại hoặc endTime trước ngày hiện tại thì return null
		if(kbNew.getStartTime().compareTo(curdate) > 0 || kbNew.getEndTime().compareTo(curdate)<0) 
			return "TIME_ERROR";
	
		return this.save(kbNew);
	}
	
	public String deleteTT(int id) {
		try {
			KhaiBao kbtt = this.khaiBaoRepo.getById(id);
			CongDan congdanTT = this.getCDByKhaiBao(kbtt);
			 
			this.khaiBaoRepo.deleteById(id);
			if(congdanTT != null) this.congDanRepo.deleteById(congdanTT.getId());
			return "Success";
			}catch (Exception e) {
				return "Error";
				// TODO: handle exception
			}
		
		
	}
	public String deleteTV(int id) {
		try {
			KhaiBao kbtv = this.khaiBaoRepo.getById(id);
			CongDan congdanTV = this.getCDByKhaiBao(kbtv);
			 
			this.khaiBaoRepo.deleteById(id);
			congdanTV.setStatus("Đang ở");
			this.congDanservice.save(congdanTV);
			return "Success";
			}catch (Exception e) {
				return "Error";
				// TODO: handle exception
			}
		
	}
	
	public Page<KhaiBao> findKhaiBao(String stt, String cccd, String fname, String lname, String sdt, Date start, Date end, Pageable pageable){
//		List<KhaiBao> listKBTT = new ArrayList<KhaiBao>();
		
		Page<KhaiBao> page = new PageImpl<KhaiBao>(new ArrayList<KhaiBao>());
		
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
		
		if( checkAllNull == args.size() && start == null && end == null) return page;
		
		//Neu co it nhat 1 arg != null => tiep tuc truy van
		
		Specification<KhaiBao> mainSpec = Specification.where(KhaiBaoSpecification.sttLike(stt))
				.and(KhaiBaoSpecification.cccdLike(args.get(0))).and(KhaiBaoSpecification.fNameLike(args.get(1)))
				.and(KhaiBaoSpecification.lNameLike(args.get(2))).and(KhaiBaoSpecification.sdtLike(args.get(3)));
		
		if(start != null && end != null) {
			Specification<KhaiBao> spec1 = mainSpec.and((KhaiBaoSpecification.StimeLessThanOrEqualTo(start))
				.and(KhaiBaoSpecification.EtimeGreaterThanOrEqualTo(end)));
			page = this.khaiBaoRepo.findAll(spec1, pageable);
		}else if(start == null && end != null) {
			Specification<KhaiBao> spec2 = mainSpec.and(KhaiBaoSpecification.StimeLessThanOrEqualTo(end));
			page = this.khaiBaoRepo.findAll(spec2, pageable);
		}else if(start != null && end == null) {
			Specification<KhaiBao> spec3 = mainSpec.and(KhaiBaoSpecification.EtimeGreaterThanOrEqualTo(start));
			page = this.khaiBaoRepo.findAll(spec3, pageable);
		}else page = this.khaiBaoRepo.findAll(mainSpec, pageable);
		
		return page;	
	}

	public List<KhaiBao> updateKhaiBaoFromCD(CongDan congDanUpdate){
		List<KhaiBao> listUpdate = new ArrayList<KhaiBao>();
		if(congDanUpdate.getCanCuocCongDan() == null) return listUpdate;
		listUpdate = this.khaiBaoRepo.findAll(Specification.where(KhaiBaoSpecification
				.cccdLike(congDanUpdate.getCanCuocCongDan())));
		for(KhaiBao i : listUpdate) this.congDanToKhaiBao(i, congDanUpdate);
		return this.khaiBaoRepo.saveAll(listUpdate);
	}
	
	public void congDanToKhaiBao(KhaiBao kb, CongDan cd) {
		kb.setCanCuocCongDan(cd.getCanCuocCongDan());
		kb.setPhoneNumber(cd.getPhoneNumber());
		kb.setFirstName(cd.getFirstName());
		kb.setLastName(cd.getLastName());
		kb.setDateOfBirth(cd.getDateOfBirth());
		kb.setGender(cd.getGender());
		kb.setJob(cd.getJob());
	}
}
