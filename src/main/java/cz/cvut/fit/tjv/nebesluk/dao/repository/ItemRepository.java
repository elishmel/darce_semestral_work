package cz.cvut.fit.tjv.nebesluk.dao.repository;

import cz.cvut.fit.tjv.nebesluk.domain.Client;
import cz.cvut.fit.tjv.nebesluk.domain.Item;
import cz.cvut.fit.tjv.nebesluk.domain.Tag;

import java.util.Collection;

public interface ItemRepository extends CrudRepository<Item,Long> {
    public Collection<Item> findAllWithTag(Tag tag);

    public Collection<Item> findAllFromClient(Client client);

    public Collection<Item> findAllRequests();

    public Collection<Item> findAllOffers();

    public Collection<Item> findAllActiveOffers();

    public Collection<Item> findAllInactiveOffers();
}
