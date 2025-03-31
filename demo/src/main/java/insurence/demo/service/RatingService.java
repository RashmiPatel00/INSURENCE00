package insurence.demo.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurence.demo.model.Rating;
import insurence.demo.model.User;
import insurence.demo.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating calculatePremium(Double quoteAmount, User broker) {
        // Example of premium calculation
        double premium = quoteAmount * 0.1; // Simple calculation, you can improve the formula
        Rating rating = new Rating();
        rating.setPremium(premium);
        rating.setCalculatedAt(LocalDateTime.now());
        rating.setBroker(broker);
        rating.setRatingParameters("Default rating parameters");
        return rating;
    }

    public List<Rating> getRatingHistoryForQuote(Long quoteId) {
        return ratingRepository.findByQuoteId(quoteId);
    }

    public List<String> getAllRatingParameters() {
        // Return all available rating parameters
        return List.of("parameter1", "parameter2", "parameter3");
    }
}

