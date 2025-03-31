package insurence.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.LOB;
import insurence.demo.service.LOBService;

@RestController
@RequestMapping("/lob")
public class LOBController {

    @Autowired
    private LOBService lobService;

    @GetMapping
    public ResponseEntity<Page<LOB>> getAllLOBs(Pageable pageable) {
        return ResponseEntity.ok(lobService.getAllLOBs(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LOB> getLOBById(@PathVariable Long id) {
        return ResponseEntity.ok(lobService.getLOBById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<LOB> createLOB(@RequestBody LOB lob) {
        return ResponseEntity.ok(lobService.createLOB(lob));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<LOB> updateLOB(@PathVariable Long id, @RequestBody LOB updatedLOB) {
        return ResponseEntity.ok(lobService.updateLOB(id, updatedLOB));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deactivateLOB(@PathVariable Long id) {
        lobService.deactivateLOB(id);
        return ResponseEntity.ok("LOB deactivated successfully");
    }
}

