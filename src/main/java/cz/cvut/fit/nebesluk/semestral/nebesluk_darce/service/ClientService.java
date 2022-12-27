package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ClientRepository;

public class ClientService extends AbstractService<Client,Long> {

    public ClientService(ClientRepository repository){
        super(repository);
    }


}
