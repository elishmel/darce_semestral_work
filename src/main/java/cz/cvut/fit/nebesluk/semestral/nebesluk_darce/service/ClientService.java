package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClientService extends AbstractService<Client,Long> {

    @Autowired
    private UserDetailsManager userDetailsManager;

    public ClientService(ClientRepository repository){
        super(repository);
    }

    public Client Register(Client client, String password){
        userDetailsManager.createUser(org.springframework.security.core.userdetails.User.
                builder().
                username(client.getUsername()).
                password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)).
                roles("USER").
                build());
        return this.Create(client);
    }

    public void UpdateCredentials(String oldUsername,String username, String password){
        var roles = userDetailsManager.loadUserByUsername(oldUsername).getAuthorities();
        userDetailsManager.deleteUser(oldUsername);
        userDetailsManager.createUser(User.builder().username(username).password(
                PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .authorities(roles).build());
    }

    public void DeleteCredentials(Long id){
        var user = this.ReadById(id);
        userDetailsManager.deleteUser(user.get().getUsername());
    }

    public Optional<Client> ReadByUsername(String username){
        return ((ClientRepository)repository).findClientByUsername(username);
    }

    @Override
    public Client Create(Client entity) {
        entity.setClient_id(((ClientRepository)repository).getMaxId().orElse((long)-1)+1);
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateLastLogon(LocalDateTime.now());
        return super.Create(entity);
    }
}
