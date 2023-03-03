package peaksoft.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.model.Hospital;

public interface hospitalRepository extends JpaRepository<Hospital,Long> {


}
