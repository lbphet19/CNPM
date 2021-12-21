package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import team.cnpm.models.KhaiBao;

public interface KhaiBaoRepository extends JpaRepository<KhaiBao, Integer>, JpaSpecificationExecutor<KhaiBao> {
//	Boolean existsByCanCuocCongDan(String canCuocCongDan);
//	@Query("select kb from KhaiBao kb where kb.startTime <= ?1 and kb.endTime >= ?2")
	
}
