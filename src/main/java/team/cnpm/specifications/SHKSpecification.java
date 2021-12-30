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
	
	public static Specification<SoHoKhau> ownerCCCDLike(String cccd){
		if(cccd == null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.equal(root.get("owner").get("canCuocCongDan"), cccd);
	}
	
	public static Specification<SoHoKhau> idSHKLike(String id){
		if(id == null) return null;
		return (root, query, criteriaBuilder)->
			criteriaBuilder.equal(root.get("id"), id);
	}
	
	
}
