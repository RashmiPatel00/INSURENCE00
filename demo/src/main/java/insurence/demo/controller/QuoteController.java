package insurence.demo.controller;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Quote;
import insurence.demo.model.User;
import insurence.demo.service.QuoteService;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public ResponseEntity<Page<Quote>> getQuotes(Pageable pageable, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.getQuotes(pageable, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.getQuoteById(id, user));
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.createQuote(quote, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote updatedQuote, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.updateQuote(id, updatedQuote, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        quoteService.deleteQuote(id, user);
        return ResponseEntity.ok("Quote deleted successfully");
    }

    @GetMapping("/{id}/parameters")
    public ResponseEntity<Quote> getQuoteParameters(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.getQuoteById(id, user));
    }

    @PutMapping("/{id}/parameters")
    public ResponseEntity<Quote> updateQuoteParameters(@PathVariable Long id, @RequestBody Quote updatedQuote, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.updateQuote(id, updatedQuote, user));
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Quote> calculatePremium(@PathVariable Long id, @RequestParam BigDecimal premium, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.calculatePremium(id, premium, user));
    }

    @PostMapping("/{id}/bind")
    public ResponseEntity<Quote> bindQuote(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.bindQuoteToPolicy(id, user));
    }

    @PostMapping("/{id}/duplicate")
    public ResponseEntity<Quote> duplicateQuote(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(quoteService.duplicateQuote(id, user));
    }
}

