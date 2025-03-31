package insurence.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurence.demo.model.AuditLog;
import insurence.demo.repository.AuditLogRepository;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Get paginated audit logs
    public List<AuditLog> getAuditLogs(int page, int size) {
        // Add pagination logic using page and size parameters
        return auditLogRepository.findAll(); // Use pagination as needed
    }

    // Get specific audit log details
    public AuditLog getAuditLogDetails(Long id) {
        return auditLogRepository.findById(id).orElse(null);
    }
}

