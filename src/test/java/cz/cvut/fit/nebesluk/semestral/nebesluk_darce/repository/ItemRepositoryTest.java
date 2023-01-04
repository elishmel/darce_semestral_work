package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Unit test
@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void findItemsByAuthor(){
        Client client = new Client(0,"Blazena","Blazka", LocalDateTime.now(),LocalDateTime.now(),null);

        Item item1 = new Item(0,"Boty","",true,true,client);

        Item item2 = new Item(1,"Kalhoty","",true,true,client);

        clientRepository.save(client);

        Assertions.assertEquals(0,itemRepository.findItemsByAuthor(client).size());

        itemRepository.save(item1);
        itemRepository.save(item2);

        Assertions.assertEquals(2,itemRepository.findItemsByAuthor(client).size());

    }


}
