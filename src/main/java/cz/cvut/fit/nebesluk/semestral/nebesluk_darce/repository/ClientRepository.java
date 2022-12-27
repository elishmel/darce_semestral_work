package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Collection<Client> findClientsByDateLastLogonBefore(Date date);

    Collection<Client> findClientsByDateLastLogonAfter(Date date);

    Collection<Client> findClientsByDateCreatedBefore(Date date);

    Collection<Client> findClientsByDateCreatedAfter(Date date);
}
