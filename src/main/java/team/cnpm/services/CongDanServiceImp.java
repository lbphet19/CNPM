package team.cnpm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.cnpm.models.CongDan;
import team.cnpm.repositories.CongDanRepository;

@Service
public class CongDanServiceImp implements CongDanService {
	@Autowired
	private CongDanRepository congDanRepo;
	
	public List<CongDan> get(){
		return this.congDanRepo.findAll();
	}
	public CongDan get(int id) {
		return this.congDanRepo.findById(id).get();
	}
	public CongDan save(CongDan congDan) {
		return this.congDanRepo.save(congDan);
	}
	public CongDan update(CongDan congDanUpdate) {
		CongDan congDan = this.congDanRepo.findById(congDanUpdate.getId()).get();
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
		return this.congDanRepo.save(congDan);
	}
	public String delete(int id) {
		try {
		this.congDanRepo.deleteById(id);
		return "success";
		}catch (Exception e) {
			return "Error";
			// TODO: handle exception
		}
	}
}
