package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService extends AbstractService<Client,Long> {

    //private UserDetailsManager userDetailsManager;

    public ClientService(ClientRepository repository){
        super(repository);
    }

    public Client Register(Client client, String password){
/*        userDetailsManager.createUser(org.springframework.security.core.userdetails.User.
                builder().
                username(client.getUsername()).
                password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)).
                roles("USER").
                build());*/
        return this.Create(client);
    }

    @Override
    public Client Create(Client entity) {
        entity.setClient_id(((ClientRepository)repository).getMaxId().orElse((long)-1)+1);
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateLastLogon(LocalDateTime.now());
        return super.Create(entity);
    }
}
