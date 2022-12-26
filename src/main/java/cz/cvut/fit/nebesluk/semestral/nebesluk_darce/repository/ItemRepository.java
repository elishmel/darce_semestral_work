package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Primary
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT item FROM Items item WHERE item.item_id in (SELECT tag FROM Tags tag WHERE tag.tag = :tag)")
    Collection<Item> GetAllByTag(Tag tag);

    @Query("SELECT item FROM Items item WHERE item.isOffer = true")
    Collection<Item> GetAllOffers();

    @Query("SELECT item FROM Items item WHERE item.isOffer = false")
    Collection<Item> GetAllRequests();

    @Query("SELECT item FROM Items item WHERE item.isActive = true")
    Collection<Item> GetAllActive();

    @Query("SELECT item FROM Items item where item.isActive = false")
    Collection<Item> GetAllInactive();

    @Query("SELECT item from Items item where item.author = :author")
    Collection<Item> GetAllFromAuthor(Client author);

    @Query("SELECT item from Items item where item.name LIKE CONCAT('%',:term,'%')")
    Collection<Item> GetAllItemsWithTermInName(String term);
}
