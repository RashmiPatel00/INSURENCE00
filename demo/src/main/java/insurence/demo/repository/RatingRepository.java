package insurence.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurence.demo.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByQuoteId(Long quoteId);
}

