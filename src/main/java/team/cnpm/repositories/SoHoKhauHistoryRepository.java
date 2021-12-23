package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import team.cnpm.models.SoHoKhauHistory;

@Repository
public interface SoHoKhauHistoryRepository extends JpaRepository<SoHoKhauHistory, Integer>, JpaSpecificationExecutor<SoHoKhauHistory>{

}
