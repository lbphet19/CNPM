package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.cnpm.models.HoKhauDongGop;

@Repository
public interface HoKhauDongGopRepository extends JpaRepository<HoKhauDongGop, Integer>{

}
