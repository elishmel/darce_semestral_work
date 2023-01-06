package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityStateException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService extends AbstractService<Item,Long>{
    ClientService clientService;
    TagService tagService;
    public ItemService(ItemRepository repository,ClientService clientService_,TagService tagService_){
        super(repository);
        clientService = clientService_;
        tagService = tagService_;
    }

    @Override
    public Item Create(Item entity) {
        entity.setItem_id(((ItemRepository)repository).getMaxId().orElse((long)-1)+1);
        return super.Create(entity);
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

    public Item ProvideItem(Long clientId,Long itemId){
        Optional<Client> optionalClient = clientService.ReadById(clientId);
        Optional<Item> optionalItem = this.ReadById(itemId);

        Client client = optionalClient.orElseThrow(EntityNotExistsException::new);
        Item item = optionalItem.orElseThrow(EntityNotExistsException::new);

        item.setReceiver(item.getAuthor());
        item.setAuthor(client);
        item.setActive(false);

        try{
            return Update(item);
        } catch (EntityStateException e){
            throw new RuntimeException(e);
        }
    }

    public Item RequestItem(Item item){
        item.setReceiver(item.getAuthor());
        item.setOffer(false);
        item.setActive(true);
        try{
            return Create(item);
        } catch(EntityStateException e){
            throw new RuntimeException(e);
        }
    }

    public Item OfferItem(Item item){
        item.setActive(true);
        item.setOffer(true);
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

    public Collection<Item> GetAllWithTagFromAuthor(Tag tag, Client author){
        return ((ItemRepository)repository).findItemsByTagsContainingAndAuthor(tag,author);
    }

    public Collection<Item> GetAllWithTags(HashSet<String> tagIds){
        var tags = tagIds.stream().
                filter(s -> tagService.ReadById(s).isPresent()).
                toList();
        Collection<Item> result = new ArrayList<>();
        tags.stream().forEach(t -> {result.addAll(((ItemRepository)repository).findItemsByTagsContaining(tagService.ReadById(t).orElseThrow(EntityNotExistsException::new)));});

        return result;
    }

    public Collection<Item> GetAllWithTermInName(String term){
        return ((ItemRepository)repository).findItemsByNameContainsIgnoreCase(term);
    }

    public Collection<Item> GetAllActive(){
        return ((ItemRepository)repository).findItemsByActiveIsTrue();
    }

    public Collection<Item> GetAll(){
        return ((ItemRepository)repository).findAll();
    }
    public Collection<Item> GetAllOffers(){
        return ((ItemRepository)repository).findItemsByOfferIsTrue();
    }

    public Collection<Item> GetAllRequests(){
        return ((ItemRepository)repository).findItemsByOfferIsFalse();
    }

    public Item GetItemById(Long id){
        Optional<Item> optionalItem = this.ReadById(id);
        return optionalItem.orElseThrow(EntityNotExistsException::new);
    }
}
