package team.cnpm.services;

import java.util.List;

import team.cnpm.models.DongGop;

public interface DongGopService {
	DongGop save(DongGop dongGop);
	DongGop update(DongGop dongGop);
	String delete(int id);
	List<DongGop> get();
}
