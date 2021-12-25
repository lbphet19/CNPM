package team.cnpm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.cnpm.models.SoHoKhauHistory;

@Repository
public interface SoHoKhauHistoryRepository extends JpaRepository<SoHoKhauHistory, Integer>, JpaSpecificationExecutor<SoHoKhauHistory>{
	@Query(value = "SELECT his.id FROM SoHoKhauHistory his",nativeQuery = true)
	List<Integer> findAllIds();
}
