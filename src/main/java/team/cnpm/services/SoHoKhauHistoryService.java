package team.cnpm.services;

import java.util.List;

import team.cnpm.models.SoHoKhauHistory;


public interface SoHoKhauHistoryService {
	List<SoHoKhauHistory> get();
	SoHoKhauHistory save(SoHoKhauHistory shkHistory);
	SoHoKhauHistory update(SoHoKhauHistory shkHistory);
	String delete(int id);
}
