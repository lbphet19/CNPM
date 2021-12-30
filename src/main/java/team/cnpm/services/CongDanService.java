package team.cnpm.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.cnpm.DTOs.request.CongDanOfSHKRequestDTO;
import team.cnpm.DTOs.request.CongDanRequestDTO;
import team.cnpm.DTOs.response.CongDanDetailDTO;
import team.cnpm.DTOs.response.CongDanOfSHK_DTO;
import team.cnpm.DTOs.response.CongDanResponseDTO;
import team.cnpm.exceptions.CongDanQuaDoiException;
import team.cnpm.models.CongDan;

public interface CongDanService {
	List<CongDan> get();
	CongDan get(int id);
	CongDan save(CongDan congDan);
	CongDan update(CongDan congDan);
	String delete(int id);
	Page<CongDan> findCongDan(String cccd, String fname, String lname, String sdt,Pageable pageable);
	CongDanResponseDTO entityToDTO(CongDan congDan);
	CongDanOfSHK_DTO entityToCDofSHK_DTO(CongDan cd);
	CongDanDetailDTO entityToDetailDTO(CongDan cd);
	Page<CongDan> findAll(Pageable pageable);
	CongDan updateRelationship(CongDan congDan);
	CongDan updateRelationship(CongDanOfSHKRequestDTO congDan);
	CongDan save(CongDanRequestDTO congDan);
	void checkPassedAwayByID(int id) throws CongDanQuaDoiException;

	void checkPassedAwayByCCCD(String cccd) throws CongDanQuaDoiException;
	CongDan AddByCCCD(String cccd) throws CongDanQuaDoiException;
	
	Page<CongDan> statistic(String gender, String ageRange, Pageable pageable);
	
}
