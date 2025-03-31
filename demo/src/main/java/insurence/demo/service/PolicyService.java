package insurence.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import insurence.demo.model.Policy;
import insurence.demo.model.User;
import insurence.demo.repository.PolicyRepository;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Page<Policy> getPolicies(Pageable pageable) {
        return policyRepository.findAll(pageable);
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }

    public Policy createPolicy(Policy policy, User admin) {
        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only Admin can create policies");
        }
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy updatedPolicy, User admin) {
        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only Admin can update policies");
        }
        Policy policy = getPolicyById(id);
        policy.setPolicyNumber(updatedPolicy.getPolicyNumber());
        policy.setStartDate(updatedPolicy.getStartDate());
        policy.setEndDate(updatedPolicy.getEndDate());
        policy.setStatus(updatedPolicy.getStatus());
        policy.setDescription(updatedPolicy.getDescription());
        return policyRepository.save(policy);
    }

    public Policy updatePolicyStatus(Long id, String status, User admin) {
        if (!admin.getRole().equals("ADMIN")) {
            throw new RuntimeException("Only Admin can update policy status");
        }
        Policy policy = getPolicyById(id);
        policy.setStatus(status);
        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
        Policy policy = getPolicyById(id);
        policyRepository.delete(policy);
    }

    // Document retrieval will be implemented here
}

