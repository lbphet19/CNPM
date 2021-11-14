package team.cnpm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.cnpm.models.DongGop;
import team.cnpm.repositories.DongGopRepository;

@Service
public class DongGopServiceImp implements DongGopService {
	@Autowired
	private DongGopRepository dongGopRepo;
	
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
