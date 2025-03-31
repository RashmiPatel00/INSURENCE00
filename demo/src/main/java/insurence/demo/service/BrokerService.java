package insurence.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import insurence.demo.model.Broker;
import insurence.demo.repository.BrokerRepository;

@Service
public class BrokerService {

    @Autowired
    private BrokerRepository brokerRepository;

    public Page<Broker> getBrokers(Pageable pageable) {
        return brokerRepository.findAll(pageable);
    }

    public double getBrokerPerformance(Long brokerId) {
        Broker broker = brokerRepository.findById(brokerId)
                .orElseThrow(() -> new RuntimeException("Broker not found"));
        return broker.getPerformanceScore();
    }
}

