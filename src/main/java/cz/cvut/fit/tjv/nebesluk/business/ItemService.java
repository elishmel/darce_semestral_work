package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ItemRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends CrudService<Item,Long>{

    protected ClientService clientService;

    public ItemService(ItemRepository itemRepository,ClientService _clientService){
        super(itemRepository);
        clientService = _clientService;
    }
}