package cz.cvut.fit.tjv.nebesluk.business;

import cz.cvut.fit.tjv.nebesluk.dao.repository.ItemRepository;
import cz.cvut.fit.tjv.nebesluk.domain.Item;

public class ItemService extends CrudService<Item,Long>{
    public ItemService(ItemRepository itemRepository){
        super(itemRepository);
    }
}