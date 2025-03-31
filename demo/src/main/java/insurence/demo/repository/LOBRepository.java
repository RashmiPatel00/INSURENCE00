package insurence.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurence.demo.model.LOB;

@Repository
public interface LOBRepository extends JpaRepository<LOB, Long> {
}

