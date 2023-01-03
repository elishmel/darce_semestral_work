package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("select max(item_id) from Items")
    public Optional<Long> getMaxId();

    Collection<Item> findItemsByTagsContaining(Tag tag);

    /**
     * Get all items that are offer
     * @return
     */
    Collection<Item> findItemsByOfferIsTrue();

    /**
     * Get all items that are requests
     * @return
     */
    Collection<Item> findItemsByOfferIsFalse();

    /**
     * Get all active items
     * @return
     */
    Collection<Item> findItemsByActiveIsTrue();

    /**
     * Get all inactive items
     * @return
     */
    Collection<Item> findItemsByActiveIsFalse();

    Collection<Item> findItemsByAuthor(Client author);

    Collection<Item> findItemsByNameContainsIgnoreCase(String term);

    Collection<Item> findItemsByReceiver(Client client);
}
