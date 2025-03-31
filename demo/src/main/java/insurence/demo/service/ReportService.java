package insurence.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurence.demo.model.Policy;
import insurence.demo.model.Quote;
import insurence.demo.model.Report;
import insurence.demo.repository.PolicyRepository;
import insurence.demo.repository.QuoteRepository;

@Service
public class ReportService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private PolicyRepository policyRepository;

    // Report: Quote statistics
    public Report getQuoteStatistics() {
        List<Quote> quotes = quoteRepository.findAll();
        // Logic to generate statistics from the quotes (e.g., count, average premium, etc.)
        return new Report("Quote Statistics", quotes);
    }

    // Report: Policy statistics
    public Report getPolicyStatistics() {
        List<Policy> policies = policyRepository.findAll();
        // Logic to generate statistics from the policies
        return new Report("Policy Statistics", policies);
    }

    // Report: Broker performance
    public Report getBrokerPerformance() {
        // Logic to generate broker performance metrics
        return new Report("Broker Performance", "Sample performance data");
    }

    // Custom Report
    public Report generateCustomReport(String reportType) {
        // Logic to generate a custom report based on the input type
        return new Report(reportType, "Custom report data");
    }
}
