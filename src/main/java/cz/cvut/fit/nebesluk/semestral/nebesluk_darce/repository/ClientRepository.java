package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("select max(client_id) from Clients")
    public Optional<Long> getMaxId();

    public Collection<Client> findClientsByDateLastLogonBefore(Date date);

    public Collection<Client> findClientsByDateLastLogonAfter(Date date);

    public Collection<Client> findClientsByDateCreatedBefore(Date date);

    public Collection<Client> findClientsByDateCreatedAfter(Date date);
}
