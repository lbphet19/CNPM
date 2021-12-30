package team.cnpm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.cnpm.DTOs.request.CongDanOfSHKRequestDTO;
import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.exceptions.OwnerNotAvailableException;
import team.cnpm.models.SoHoKhau;

public interface SoHoKhauService {
	List<SoHoKhau> get();
	SoHoKhau save(SoHoKhau shk);
	SoHoKhau update(SoHoKhau shk);
	SoHoKhau update(SoHoKhauRequestDTO hoKhauUpdateRequestDTO);
	String delete(int id);
	SoHoKhau updateMembers(SoHoKhau shk, int ownerId, List<CongDanOfSHKRequestDTO> membersId) throws OwnerNotAvailableException;
	SoHoKhau dtoToEntity(SoHoKhauRequestDTO dto);

	SoHoKhauResponseDTO entityToDTO(SoHoKhau shk);
	SoHoKhauDetailDTO entityToDetailDTO(SoHoKhau shk);
	SoHoKhau getByID(String i);
	Page<SoHoKhau> findSHKByName(String id, String fname, String lname, String cccd,Pageable pageable);

	
}

