package insurence.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Policy;
import insurence.demo.model.User;
import insurence.demo.service.PolicyService;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping
    public ResponseEntity<Page<Policy>> getPolicies(Pageable pageable) {
        return ResponseEntity.ok(policyService.getPolicies(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy, Authentication auth) {
        User admin = (User) auth.getPrincipal();
        return ResponseEntity.ok(policyService.createPolicy(policy, admin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy updatedPolicy, Authentication auth) {
        User admin = (User) auth.getPrincipal();
        return ResponseEntity.ok(policyService.updatePolicy(id, updatedPolicy, admin));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Policy> updatePolicyStatus(@PathVariable Long id, @RequestParam String status, Authentication auth) {
        User admin = (User) auth.getPrincipal();
        return ResponseEntity.ok(policyService.updatePolicyStatus(id, status, admin));
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<String> getPolicyDocuments(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        // Implement the logic for document retrieval here
        return ResponseEntity.ok("Policy documents retrieved");
    }
}

