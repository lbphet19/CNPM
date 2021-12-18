package team.cnpm.specifications;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import team.cnpm.models.DongGop;

public class DongGopSpecification {
	
	public static Specification<DongGop> eventNameLike(String name){
		if(name == null) return null;
		else return (root, query, criteriaBuilder)->
			criteriaBuilder.like(root.get("eventName"), "%"+name+"%");
	}
	
	public static Specification<DongGop> dateLike(Date date){
		if(date == null) return null;
		else return (root, query, criteriaBuilder)->
			criteriaBuilder.equal(root.get("date"), date);
	}
	
}
