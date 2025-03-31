package insurence.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurence.demo.model.Quote;
import insurence.demo.model.User;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByBroker(User broker);
}

