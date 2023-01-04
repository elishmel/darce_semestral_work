package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;

import java.time.LocalDateTime;
import java.util.Optional;
// Integration test
@SpringBootTest
public class ItemServiceTest {

    @MockBean
    ClientService clientService;

    @Autowired
    ItemService itemService;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void itemCanBeReceivedByClient(){
        Client clientAuthor = new Client();
        clientAuthor.setClient_id(0);
        clientAuthor.setRealName("Petr");
        clientAuthor.setProfilePicture(null);
        clientAuthor.setUsername("petr");
        clientAuthor.setDateCreated(LocalDateTime.now());
        clientAuthor.setDateLastLogon(LocalDateTime.now());

        Client clientReceiver = new Client();
        clientReceiver.setClient_id(1);
        clientReceiver.setRealName("Jana");
        clientReceiver.setProfilePicture(null);
        clientReceiver.setUsername("jana");
        clientReceiver.setDateCreated(LocalDateTime.now());
        clientReceiver.setDateLastLogon(LocalDateTime.now());

        Item item = new Item();
        item.setActive(true);
        item.setName("Bunda");
        item.setItem_id(0);
        item.setOffer(true);
        item.setDescription("Tepla bunda");
        item.setAuthor(clientAuthor);

        Mockito.when(clientService.ReadById((long)1)).thenReturn(Optional.of(clientReceiver));
        Mockito.when(itemService.ReadById((long)0)).thenReturn(Optional.of(item));
        Mockito.when(itemRepository.existsById((long)0)).thenReturn(true);

        itemService.ReceiveItem((long)1,(long)0);

        Mockito.verify(itemRepository,Mockito.times(1)).save(item);
        Assertions.assertTrue(item.getReceiver().equals(clientReceiver));

    }

}
