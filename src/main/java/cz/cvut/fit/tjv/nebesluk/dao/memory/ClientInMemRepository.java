package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ClientRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Collection;

public class ClientInMemRepository extends InMemoryRepository<Client,Long> implements ClientRepository{
    @Override
    public Collection<Client> findAllClientsRegisteredBefore(LocalDateTime date) {
        return findAll().parallelStream().filter(
                client -> client.getDateCreated().isBefore(date)
        ).toList();
    }

    @Override
    public Collection<Client> findAllClientsRegisteredAfter(LocalDateTime date) {
        return findAll().parallelStream().filter(
                client -> client.getDateCreated().isAfter(date)
        ).toList();
    }

    @Override
    public Collection<Client> findAllClientsInactiveFor(int days) {
        var date = LocalDateTime.now().minusDays(days);
        return  findAll().parallelStream().filter(
                client -> client.getDateLastLogon().isBefore(date)
        ).toList();
    }
}
