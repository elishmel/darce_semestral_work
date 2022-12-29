package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityStateException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ItemService extends AbstractService<Item,Long>{
    ClientService clientService;
    TagService tagService;
    public ItemService(ItemRepository repository,ClientService clientService_,TagService tagService){
        super(repository);
        clientService = clientService_;
    }

    public Item ReceiveItem(Long clientId,Long itemId){
        Optional<Client> optionalClient = clientService.ReadById(clientId);
        Optional<Item> optionalItem = this.ReadById(itemId);

        Client client = optionalClient.orElseThrow(EntityNotExistsException::new);
        Item item = optionalItem.orElseThrow(EntityNotExistsException::new);

        item.setReceiver(client);
        item.setActive(false);

        try{
            return Update(item);
        } catch (EntityStateException e){
            throw new RuntimeException(e);
        }
    }

    public Item RequestItem(Long clientId, Item item){
        Optional<Client> optionalClient = clientService.ReadById(clientId);

        Client client = optionalClient.orElseThrow(EntityNotExistsException::new);
        item.setReceiver(client);
        item.setOffer(false);
        item.setActive(true);
        item.setAuthor(client);
        try{
            return Create(item);
        } catch(EntityStateException e){
            throw new RuntimeException(e);
        }
    }

    public Item OfferItem(Long clientId, Item item){
        Optional<Client> optionalClient = clientService.ReadById(clientId);

        Client client = optionalClient.orElseThrow(EntityNotExistsException::new);
        item.setActive(true);
        item.setOffer(true);
        item.setAuthor(client);
        try {
            return Create(item);
        } catch (EntityStateException e){
            throw new RuntimeException(e);
        }
    }
    public Collection<Item> GetAllFromAuthor(Long clientId){

        Optional<Client> optionalClient = clientService.ReadById(clientId);

        Client client = optionalClient.orElseThrow(EntityNotExistsException::new);

        return ((ItemRepository)repository).findItemsByAuthor(client);
    }

    public Collection<Item> GetAllWithTag(String tagId){
        Optional<Tag> optionalTag = tagService.ReadById(tagId);

        Tag tag = optionalTag.orElseThrow(EntityNotExistsException::new);

        return ((ItemRepository)repository).findItemsByTagsContaining(tag);
    }

    public Collection<Item> GetAllWithTags(HashSet<String> tagIds){
        var tags = tagIds.stream().
                filter(s -> tagService.ReadById(s).isPresent()).
                toList();
        List<Item> result = Arrays.asList();
        for (var t : tags) {
            result.addAll(((ItemRepository)repository).findItemsByTagsContaining(tagService.ReadById(t).get()));
        }

        if(!result.isEmpty()){
            return result;
        }

        throw new EntityNotExistsException("No valid tags");
    }

    public Collection<Item> GetAllWithTermInName(String term){
        return ((ItemRepository)repository).findItemsByNameContains(term);
    }

    public Collection<Item> GetAllOffers(){
        return ((ItemRepository)repository).findItemsByOfferIsTrue();
    }

    public Collection<Item> GetAllRequessts(){
        return ((ItemRepository)repository).findItemsByOfferIsFalse();
    }

    public Item GetItemById(Long id){
        Optional<Item> optionalItem = this.ReadById(id);
        return optionalItem.orElseThrow(EntityNotExistsException::new);
    }
}
