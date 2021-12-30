package team.cnpm.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.cnpm.DTOs.response.SHKHistoryResponseDTO;
import team.cnpm.models.SoHoKhauHistory;


public interface SoHoKhauHistoryService {
	List<SoHoKhauHistory> get();
	SoHoKhauHistory save(SoHoKhauHistory shkHistory);
	SoHoKhauHistory update(SoHoKhauHistory shkHistory);
	String delete(int id);
	
	SHKHistoryResponseDTO shkHisToShkHisDTO(SoHoKhauHistory shkHis);
	SoHoKhauHistory getByID(int i);
	Page<SoHoKhauHistory> findSHKHistory(String cccd, String idHoKhau, Date stime, Date etime, Pageable pageable);
}
