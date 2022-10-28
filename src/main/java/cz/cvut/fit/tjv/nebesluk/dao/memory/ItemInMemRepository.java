package cz.cvut.fit.tjv.nebesluk.dao.memory;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ItemRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Client;
import cz.cvut.fit.tjv.nebesluk.domain.Item;
import cz.cvut.fit.tjv.nebesluk.domain.Tag;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ItemInMemRepository extends InMemoryRepository<Item,Long> implements ItemRepository {
    public Collection<Item> findAllWithTag(Tag tag){
        return findAll().parallelStream().filter(
                item -> item.hasTag(tag)
        ).toList();
    }

    public Collection<Item> findAllFromClient(Client client){
        return findAll().parallelStream().filter(
                item -> item.getAuthor().equals(client)
        ).toList();
    }

    public Collection<Item> findAllRequests(){
        return findAll().parallelStream().filter(
                item -> !item.getOffer()
        ).toList();
    }

    public Collection<Item> findAllOffers(){
        return findAll().parallelStream().filter(
                Item::getOffer
        ).toList();
    }

    public Collection<Item> findAllActiveOffers(){
        return findAll().parallelStream().filter(
                Item::getActive
        ).toList();
    }

    public Collection<Item> findAllInactiveOffers(){
        return findAll().parallelStream().filter(
                item -> !item.getActive()
        ).toList();
    }
}
