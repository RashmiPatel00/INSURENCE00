package insurence.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SystemService {

    // Health check
    public String getHealthStatus() {
        return "System is healthy";
    }

    // Get API version
    public String getApiVersion() {
        return "API Version 1.0.0";
    }
}

