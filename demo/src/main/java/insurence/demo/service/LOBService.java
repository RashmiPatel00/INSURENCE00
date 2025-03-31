package insurence.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import insurence.demo.model.LOB;
import insurence.demo.repository.LOBRepository;

@Service
public class LOBService {

    @Autowired
    private LOBRepository lobRepository;

    public Page<LOB> getAllLOBs(Pageable pageable) {
        return lobRepository.findAll(pageable);
    }

    public LOB getLOBById(Long id) {
        return lobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LOB not found"));
    }

    public LOB createLOB(LOB lob) {
        return lobRepository.save(lob);
    }

    public LOB updateLOB(Long id, LOB updatedLOB) {
        LOB lob = getLOBById(id);
        lob.setName(updatedLOB.getName());
        lob.setDescription(updatedLOB.getDescription());
        return lobRepository.save(lob);
    }

    public void deactivateLOB(Long id) {
        LOB lob = getLOBById(id);
        lob.setActive(false);
        lobRepository.save(lob);
    }
}

