package insurence.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.AuditLog;
import insurence.demo.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    // Get paginated audit logs
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAuditLogs(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        List<AuditLog> logs = auditService.getAuditLogs(page, size);
        return ResponseEntity.ok(logs);
    }

    // Get specific audit log details
    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getAuditLogDetails(@PathVariable Long id) {
        AuditLog auditLog = auditService.getAuditLogDetails(id);
        return auditLog != null ? ResponseEntity.ok(auditLog) : ResponseEntity.notFound().build();
    }
}

