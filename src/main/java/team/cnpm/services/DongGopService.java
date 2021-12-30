package team.cnpm.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.cnpm.DTOs.response.DongGopDetailsDTO;
import team.cnpm.DTOs.response.ListDongGopDTO;
import team.cnpm.models.DongGop;

public interface DongGopService {
	DongGop save(DongGop dongGop);
	DongGop update(DongGop dongGop);
	String delete(int id);
	List<DongGop> get();
	Page<DongGop> findAll(Pageable pageable);
	ListDongGopDTO entityToDTO(DongGop dg);
	DongGop getbyid(int id);
	DongGopDetailsDTO entityToDetailsDTOroi(DongGop dg1);
	DongGopDetailsDTO entityToDetailsDTOchua(DongGop dg1);
	Page<DongGop> findEvent(String name, Date date, Pageable pageable);
	
}
