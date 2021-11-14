package team.cnpm.services;

import java.util.List;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.models.SoHoKhau;

public interface SoHoKhauService {
	List<SoHoKhau> get();
	SoHoKhau save(SoHoKhau shk);
	SoHoKhau update(SoHoKhau shk);
	SoHoKhau update(SoHoKhauRequestDTO hoKhauUpdateRequestDTO);
	String delete(int id);
	SoHoKhau updateMembers(SoHoKhau shk, Integer ownerId, List<Integer> membersId);
	SoHoKhau dtoToEntity(SoHoKhauRequestDTO dto);
}

