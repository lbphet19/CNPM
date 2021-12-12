package team.cnpm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.cnpm.models.SoHoKhau;

@Repository
public interface SoHoKhauRepository extends JpaRepository<SoHoKhau, String>, JpaSpecificationExecutor<SoHoKhau> {
	@Query("SELECT shk FROM SoHoKhau shk WHERE shk.id = ?1")
	SoHoKhau findBySHKId(String id);
}
