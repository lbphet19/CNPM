package team.cnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.cnpm.models.DongGop;

@Repository
public interface DongGopRepository extends JpaRepository<DongGop, Integer> {

}
