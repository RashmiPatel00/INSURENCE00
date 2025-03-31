package insurence.demo.service;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import insurence.demo.model.Quote;
import insurence.demo.model.User;
import insurence.demo.repository.QuoteRepository;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public Page<Quote> getQuotes(Pageable pageable, User user) {
        if (user.getRole().equals("ADMIN")) {
            return quoteRepository.findAll(pageable);
        } else {
            return new PageImpl<>(quoteRepository.findByBroker(user));
        }
    }

    public Quote getQuoteById(Long id, User user) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        if (user.getRole().equals("ADMIN") || quote.getBroker().equals(user)) {
            return quote;
        }
        throw new RuntimeException("Unauthorized access");
    }

    public Quote createQuote(Quote quote, User broker) {
        quote.setBroker(broker);
        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Long id, Quote updatedQuote, User user) {
        Quote quote = getQuoteById(id, user);
        quote.setDescription(updatedQuote.getDescription());
        quote.setPremiumAmount(updatedQuote.getPremiumAmount());
        return quoteRepository.save(quote);
    }

    public void deleteQuote(Long id, User user) {
        Quote quote = getQuoteById(id, user);
        quoteRepository.delete(quote);
    }

    public Quote calculatePremium(Long id, BigDecimal premium, User user) {
        Quote quote = getQuoteById(id, user);
        quote.setPremiumAmount(premium);
        return quoteRepository.save(quote);
    }

    public Quote bindQuoteToPolicy(Long id, User user) {
        Quote quote = getQuoteById(id, user);
        quote.setBound(true);
        return quoteRepository.save(quote);
    }

    public Quote duplicateQuote(Long id, User user) {
        Quote original = getQuoteById(id, user);
        Quote duplicate = new Quote();
        duplicate.setDescription(original.getDescription());
        duplicate.setClient(original.getClient());
        duplicate.setBroker(original.getBroker());
        duplicate.setPremiumAmount(original.getPremiumAmount());
        return quoteRepository.save(duplicate);
    }
}

