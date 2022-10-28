package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ClientRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends CrudService<Client,Long> {
    public ClientService(ClientRepository clientRepository){
        super(clientRepository);
    }
}