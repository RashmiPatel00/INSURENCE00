package insurence.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurence.demo.model.Client;
import insurence.demo.model.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByBroker(User broker);
}

