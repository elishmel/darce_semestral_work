package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractService<Item,Long>{
    public ItemService(ItemRepository repository){
        super(repository);
    }
}
