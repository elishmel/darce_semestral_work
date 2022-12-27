package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Collection<Client> findClientsByDateLastLogonBefore(Date date);

    Collection<Client> findClientsByDateLastLogonAfter(Date date);

    Collection<Client> findClientsByDateCreatedBefore(Date date);

    Collection<Client> findClientsByDateCreatedAfter(Date date);
}
