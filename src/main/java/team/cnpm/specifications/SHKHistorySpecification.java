package team.cnpm.specifications;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import team.cnpm.models.SoHoKhauHistory;

public class SHKHistorySpecification {
	
	public static Specification<SoHoKhauHistory> sttLike(String stt){
		if(stt==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("status"), stt);
	}
	
	public static Specification<SoHoKhauHistory> cccdLike(String cccd){
		if(cccd==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("congDan").get("canCuocCongDan"), cccd);
	}
	
	public static Specification<SoHoKhauHistory> fNameLike(String fname){
		if(fname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("congDan").get("firstName"), "%"+fname+"%");
	}
	
	public static Specification<SoHoKhauHistory> lNameLike(String lname){
		if(lname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("congDan").get("lastName"), "%"+lname+"%");
	}
	
	public static Specification<SoHoKhauHistory> idRoiDiLike(String idRoiDi){
		if(idRoiDi==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("hoKhauRoiDi").get("id"), idRoiDi);
	}
	
	public static Specification<SoHoKhauHistory> idChuyenDenLike(String idChuyenDen){
		if(idChuyenDen==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("hoKhauChuyenDen").get("id"), idChuyenDen);
	}
	
	public static Specification<SoHoKhauHistory> dateLessThanOrEqualTo(Date time){
		if(time==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.lessThanOrEqualTo(root.get("date"), time);
	}
	
	public static Specification<SoHoKhauHistory> dateGreaterThanOrEqualTo(Date time){
		if(time==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.greaterThanOrEqualTo(root.get("date"), time);
	}

}
