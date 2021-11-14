package team.cnpm.services;

import java.util.List;

import team.cnpm.models.CongDan;

public interface CongDanService {
	List<CongDan> get();
	CongDan get(int id);
	CongDan save(CongDan congDan);
	CongDan update(CongDan congDan);
	String delete(int id);
}
