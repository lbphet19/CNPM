package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.cnpm.models.SoHoKhau;

@Repository
public interface SoHoKhauRepository extends JpaRepository<SoHoKhau, Integer> {

}
