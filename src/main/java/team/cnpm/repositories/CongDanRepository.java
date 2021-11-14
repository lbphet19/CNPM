package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.cnpm.models.CongDan;

@Repository
public interface CongDanRepository extends JpaRepository<CongDan, Integer> {
	Boolean existsByCanCuocCongDan(String canCuocCongDan);
}
