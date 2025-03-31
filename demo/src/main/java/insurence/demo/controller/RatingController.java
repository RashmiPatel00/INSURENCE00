package insurence.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Rating;
import insurence.demo.model.User;
import insurence.demo.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/calculate")
    public ResponseEntity<Rating> calculatePremium(@RequestParam Double quoteAmount, Authentication auth) {
        User broker = (User) auth.getPrincipal();
        Rating rating = ratingService.calculatePremium(quoteAmount, broker);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/parameters")
    public ResponseEntity<List<String>> getAllRatingParameters() {
        return ResponseEntity.ok(ratingService.getAllRatingParameters());
    }

    @GetMapping("/history/{quote_id}")
    public ResponseEntity<List<Rating>> getRatingHistoryForQuote(@PathVariable Long quote_id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        // Check if the user is the broker who created the quote or admin
        if (!user.getRole().equals("ADMIN")) {
            // Perform logic to check if the user is the broker for the given quote_id
        }
        List<Rating> ratings = ratingService.getRatingHistoryForQuote(quote_id);
        return ResponseEntity.ok(ratings);
    }
}

