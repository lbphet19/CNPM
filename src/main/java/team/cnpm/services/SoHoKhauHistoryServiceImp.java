package team.cnpm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.cnpm.models.SoHoKhauHistory;
import team.cnpm.repositories.SoHoKhauHistoryRepository;

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

}
