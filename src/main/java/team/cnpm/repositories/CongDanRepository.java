package team.cnpm.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import team.cnpm.models.CongDan;

@Repository
public interface CongDanRepository extends JpaRepository<CongDan, Integer>, JpaSpecificationExecutor<CongDan> {
	Boolean existsByCanCuocCongDan(String canCuocCongDan);
	
}
