package team.cnpm.specifications;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import team.cnpm.models.CongDan;
import team.cnpm.models.KhaiBao;

public class KhaiBaoSpecification {

	public static Specification<KhaiBao> sttLike(String stt){
		if(stt==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("status"), stt);
	}
	
	public static Specification<KhaiBao> cccdLike(String cccd){
		if(cccd==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("canCuocCongDan"), cccd);
	}
	
	public static Specification<KhaiBao> fNameLike(String fname){
		if(fname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("firstName"), "%"+fname+"%");
	}
	
	public static Specification<KhaiBao> lNameLike(String lname){
		if(lname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("lastName"), "%"+lname+"%");
	}
	
	public static Specification<KhaiBao> sdtLike(String sdt){
		if(sdt==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("phoneNumber"), sdt);
	}
	
	public static Specification<KhaiBao> StimeLessThanOrEqualTo(Date time){
		if(time==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.lessThanOrEqualTo(root.get("startTime"), time);
	}
	
	
	public static Specification<KhaiBao> EtimeGreaterThanOrEqualTo(Date time){
		if(time==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.greaterThanOrEqualTo(root.get("endTime"), time);
	}
	
	public static Specification<KhaiBao> EtimeLessThan(Date time){
		if(time==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.lessThan(root.get("endTime"), time);
	}
}
