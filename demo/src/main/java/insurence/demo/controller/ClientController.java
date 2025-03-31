package insurence.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurence.demo.model.Client;
import insurence.demo.model.User;
import insurence.demo.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<Client>> getClients(Pageable pageable, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(clientService.getClients(pageable, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(clientService.getClientById(id, user));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(clientService.createClient(client, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient, Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(clientService.updateClient(id, updatedClient, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateClient(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        clientService.deactivateClient(id, user);
        return ResponseEntity.ok("Client deactivated successfully");
    }
}

