package team.cnpm.specifications;

import org.springframework.data.jpa.domain.Specification;

import team.cnpm.models.SoHoKhau;

public class SHKSpecification {

	public static Specification<SoHoKhau> ownerFnameLike(String fname){
		if(fname == null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("owner").get("firstName"), "%"+fname+"%");
	}
	
	public static Specification<SoHoKhau> ownerLnameLike(String lname){
		if(lname == null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("owner").get("lastName"), "%"+lname+"%");
	}
}