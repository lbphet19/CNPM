package team.cnpm.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.cnpm.models.CongDan;
import team.cnpm.models.KhaiBao;

@Repository
public interface CongDanRepository extends JpaRepository<CongDan, Integer>, JpaSpecificationExecutor<CongDan> {
	Boolean existsByCanCuocCongDan(String canCuocCongDan);
	
	@Query("select cd from CongDan cd where cd.canCuocCongDan = ?1")
	CongDan getByCCCD(String cccd);
	
}
