package team.cnpm.specifications;

import org.springframework.data.jpa.domain.Specification;

import team.cnpm.models.CongDan;

public class CongDanSpecification {
	public static Specification<CongDan> cccdLike(String cccd){
		if(cccd==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("canCuocCongDan"), cccd);
	}
	
	public static Specification<CongDan> fNameLike(String fname){
		if(fname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("firstName"), "%"+fname+"%");
	}
	
	public static Specification<CongDan> lNameLike(String lname){
		if(lname==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("lastName"), "%"+lname+"%");
	}
	
	public static Specification<CongDan> sdtLike(String sdt){
		if(sdt==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("phoneNumber"), sdt);
	}
	
	public static Specification<CongDan> genderLike(String gender){
		if(gender==null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("gender"), gender);
	}
	
	public static Specification<CongDan> birthYearGreaterThan(int year){
		return (root, query, criteriaBuilder)-> criteriaBuilder.greaterThanOrEqualTo(
				criteriaBuilder.function("YEAR", Integer.class, root.get("dateOfBirth")), year);
	}
	
	public static Specification<CongDan> birthYearLessThan(int year){
		return (root, query, criteriaBuilder)-> criteriaBuilder.lessThanOrEqualTo(
				criteriaBuilder.function("YEAR", Integer.class, root.get("dateOfBirth")), year);
	}
	
}
