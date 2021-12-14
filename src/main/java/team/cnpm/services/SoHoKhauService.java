package team.cnpm.services;

import java.util.List;

import team.cnpm.DTOs.request.CongDanOfSHKRequestDTO;
import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.models.SoHoKhau;

public interface SoHoKhauService {
	List<SoHoKhau> get();
	SoHoKhau save(SoHoKhau shk);
	SoHoKhau update(SoHoKhau shk);
	SoHoKhau update(SoHoKhauRequestDTO hoKhauUpdateRequestDTO);
	String delete(int id);
	SoHoKhau updateMembers(SoHoKhau shk, int ownerId, List<CongDanOfSHKRequestDTO> membersId);
	SoHoKhau dtoToEntity(SoHoKhauRequestDTO dto);

	SoHoKhauResponseDTO entityToDTO(SoHoKhau shk);
	SoHoKhauDetailDTO entityToDetailDTO(SoHoKhau shk);
	SoHoKhau getByID(String i);
	List<SoHoKhau> findSHKByName(String fname, String lname);
	
}

