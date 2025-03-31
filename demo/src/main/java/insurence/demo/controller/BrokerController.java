package insurence.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Broker;
import insurence.demo.service.BrokerService;

@RestController
@RequestMapping("/brokers")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @GetMapping
    public ResponseEntity<Page<Broker>> getBrokers(Pageable pageable) {
        return ResponseEntity.ok(brokerService.getBrokers(pageable));
    }

    @GetMapping("/performance")
    public ResponseEntity<Double> getBrokerPerformance(@RequestParam Long brokerId, Authentication auth) {
        return ResponseEntity.ok(brokerService.getBrokerPerformance(brokerId));
    }
}
