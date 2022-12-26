package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT client FROM Clients client where client.dateLastLogon < :date")
    Collection<Client> GetAllLogedBefore(Date date);

    @Query("SELECT client from Clients client where client.dateLastLogon > :date")
    Collection<Client> GetAllLogedAfter(Date date);

    @Query("select client from Clients client where client.dateCreated < :date")
    Collection<Client> GetAllCreatedBefore(Date date);

    @Query("select client from Clients client where client.dateCreated > :date")
    Collection<Client> GetAllCreatedAfter(Date date);
}
