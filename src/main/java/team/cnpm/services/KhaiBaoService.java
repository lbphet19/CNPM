package team.cnpm.services;

import java.sql.Date;
import java.util.List;

import team.cnpm.models.CongDan;
import team.cnpm.models.KhaiBao;

public interface KhaiBaoService {
	List<KhaiBao> getTamTru();
	List<KhaiBao> getTamVang();
	KhaiBao get(int id);
	KhaiBao save(KhaiBao kb);
	CongDan khaiBaoTTToCongDan(KhaiBao kb);
	CongDan khaiBaoTVToCongDan(KhaiBao kb);
	CongDan getCDByKhaiBao(KhaiBao kb);
	Object updateTamTru_TamVang(KhaiBao kb);
	String deleteTT(int id);
	String deleteTV(int id);
	List<KhaiBao> findKhaiBao(String stt, String cccd, String fname, String lname, String sdt, Date start, Date end);
	List<KhaiBao> updateKhaiBaoFromCD(CongDan congDanUpdate);
}
