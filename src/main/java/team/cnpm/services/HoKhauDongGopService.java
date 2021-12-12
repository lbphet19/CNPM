package team.cnpm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.cnpm.DTOs.response.HKDongGopDTO;
import team.cnpm.models.HoKhauDongGop;

public interface HoKhauDongGopService {

	String delete(int id);

	HoKhauDongGop update(HoKhauDongGop HoKhauDongGopUpdate);

	HoKhauDongGop save(HoKhauDongGop HoKhauDongGop);

	List<HoKhauDongGop> get();

	HoKhauDongGop getbyid(int id);

	Page<HoKhauDongGop> findAll(Pageable pageable);
	
	HKDongGopDTO HKDongGopToHKDongGopDTO(HoKhauDongGop hkdg);

}
