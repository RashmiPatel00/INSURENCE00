package insurence.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import insurence.demo.model.Client;
import insurence.demo.model.User;
import insurence.demo.repository.ClientRepository;
import insurence.demo.repository.UserRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Client> getClients(Pageable pageable, User user) {
        if (user.getRole().equals("ADMIN")) {
            return clientRepository.findAll(pageable);
        } else {
            return new PageImpl<>(clientRepository.findByBroker(user));
        }
    }

    public Client getClientById(Long id, User user) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (user.getRole().equals("ADMIN") || client.getBroker().equals(user)) {
            return client;
        }
        throw new RuntimeException("Unauthorized access");
    }

    public Client createClient(Client client, User broker) {
        client.setBroker(broker);
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient, User user) {
        Client client = getClientById(id, user);
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhoneNumber(updatedClient.getPhoneNumber());
        return clientRepository.save(client);
    }

    public void deactivateClient(Long id, User user) {
        Client client = getClientById(id, user);
        client.setActive(false);
        clientRepository.save(client);
    }
}

