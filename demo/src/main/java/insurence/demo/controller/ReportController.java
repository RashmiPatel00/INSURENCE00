package insurence.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Report;
import insurence.demo.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint: Quote statistics
    @GetMapping("/quotes")
    public ResponseEntity<Report> getQuoteStatistics(Authentication auth) {
        // Check if the user is a Broker (own) or Admin
        // Perform authorization checks based on user roles
        Report report = reportService.getQuoteStatistics();
        return ResponseEntity.ok(report);
    }

    // Endpoint: Policy statistics
    @GetMapping("/policies")
    public ResponseEntity<Report> getPolicyStatistics(Authentication auth) {
        // Check if the user is a Broker (own) or Admin
        // Perform authorization checks based on user roles
        Report report = reportService.getPolicyStatistics();
        return ResponseEntity.ok(report);
    }

    // Endpoint: Broker performance
    @GetMapping("/brokers")
    public ResponseEntity<Report> getBrokerPerformance(Authentication auth) {
        // Check if the user is an Admin
        Report report = reportService.getBrokerPerformance();
        return ResponseEntity.ok(report);
    }

    // Endpoint: Generate custom report
    @PostMapping("/custom")
    public ResponseEntity<Report> generateCustomReport(@RequestParam String reportType, Authentication auth) {
        // Check if the user is an Admin
        Report report = reportService.generateCustomReport(reportType);
        return ResponseEntity.ok(report);
    }
}
