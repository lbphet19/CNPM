package team.cnpm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.response.HKDongGopDTO;
import team.cnpm.models.HoKhauDongGop;
import team.cnpm.repositories.HoKhauDongGopRepository;


@Service
public class HoKhauDongGopServiceImp implements HoKhauDongGopService {

	
	@Autowired
	private HoKhauDongGopRepository HoKhauDongGopRepo;
	
	@Override
	public Page<HoKhauDongGop> findAll(Pageable pageable) {
		
		return this.HoKhauDongGopRepo.findAll(pageable);
	}
	@Override
	public HoKhauDongGop getbyid(int id){
		return this.HoKhauDongGopRepo.getById(id);
	}
	@Override
	public List<HoKhauDongGop> get(){
		return this.HoKhauDongGopRepo.findAll();
	}
	
	@Override
	public HoKhauDongGop save(HoKhauDongGop HoKhauDongGop) {
		return this.HoKhauDongGopRepo.save(HoKhauDongGop);
	}
	@Override
	public HoKhauDongGop update(HoKhauDongGop HoKhauDongGopUpdate) {
		HoKhauDongGop HoKhauDongGop = this.HoKhauDongGopRepo.findById(HoKhauDongGopUpdate.getId()).get();
		HoKhauDongGop.setId(HoKhauDongGopUpdate.getId());
		HoKhauDongGop.setSoHoKhau(HoKhauDongGopUpdate.getSoHoKhau());
		HoKhauDongGop.setDongGop(HoKhauDongGopUpdate.getDongGop());
		HoKhauDongGop.setAmount(HoKhauDongGopUpdate.getAmount());
		return this.HoKhauDongGopRepo.save(HoKhauDongGop);
	}

	
	
	@Override
	public HKDongGopDTO HKDongGopToHKDongGopDTO(HoKhauDongGop hkdg) {
		return new HKDongGopDTO(hkdg.getSoHoKhau().getId(),
				hkdg.getSoHoKhau().getOwner().getFirstName()+" "+hkdg.getSoHoKhau().getOwner().getLastName(),
				hkdg.getSoHoKhau().getAddress(),
				hkdg.getSoHoKhau().getOwner().getPhoneNumber(),
				hkdg.getAmount());
	}
	
	
	
	
	@Override
	public String delete(int id) {
		try {
			this.HoKhauDongGopRepo.deleteById(id);
			return "Success";
		} catch (Exception e) {
			return "Error!";
			// TODO: handle exception
		}
	}

}
