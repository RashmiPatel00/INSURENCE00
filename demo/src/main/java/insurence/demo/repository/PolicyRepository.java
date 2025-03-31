package insurence.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurence.demo.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    // Additional query methods can be added here
}

