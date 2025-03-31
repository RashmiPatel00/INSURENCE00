package insurence.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.service.SystemService;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    // System health check
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok(systemService.getHealthStatus());
    }

    // API version info
    @GetMapping("/version")
    public ResponseEntity<String> getApiVersion() {
        return ResponseEntity.ok(systemService.getApiVersion());
    }
}
