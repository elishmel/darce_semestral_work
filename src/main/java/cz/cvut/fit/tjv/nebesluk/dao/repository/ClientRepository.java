package cz.cvut.fit.tjv.nebesluk.dao.repository;

import cz.cvut.fit.tjv.nebesluk.domain.Client;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ClientRepository extends CrudRepository<Client,Long> {
    public Collection<Client> findAllClientsRegisteredBefore(LocalDateTime date);

    public Collection<Client> findAllClientsRegisteredAfter(LocalDateTime date);

    public Collection<Client> findAllClientsInactiveFor(int days);
}
