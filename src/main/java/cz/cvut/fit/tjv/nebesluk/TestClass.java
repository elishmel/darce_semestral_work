package cz.cvut.fit.tjv.nebesluk;

import cz.cvut.fit.tjv.nebesluk.business.ClientService;
import cz.cvut.fit.tjv.nebesluk.business.ImageService;
import cz.cvut.fit.tjv.nebesluk.business.ItemService;
import cz.cvut.fit.tjv.nebesluk.business.TagService;
import cz.cvut.fit.tjv.nebesluk.domain.Client;
import cz.cvut.fit.tjv.nebesluk.domain.Image;
import cz.cvut.fit.tjv.nebesluk.domain.Item;
import cz.cvut.fit.tjv.nebesluk.domain.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URI;
import java.time.LocalDateTime;

public class TestClass {

    public static void main(String[] args) {

        Image pfp = new Image(0, URI.create("https://imgs.xkcd.com/comics/pointers.png"));

        Image image = new Image(1,URI.create("https://imgs.xkcd.com/comics/archimedes_principle_2x.png"));

        Client c1 = new Client(0,
                "nebesluk","hash",
                "Lukas Nebesky", LocalDateTime.now(),
                LocalDateTime.now(),pfp);

        Client c2 = new Client(1,
                "test","hash",
                "Test Test", LocalDateTime.now(),
                LocalDateTime.now(),null);

        Tag t1 = new Tag("Food");
        Tag t2 = new Tag("Toy");
        Tag t3 = new Tag("Sweet");

        Item i1 = new Item(0,"Dortik","Sladky dortik",true,true,c1);

        Item i2 = new Item(1,"Hraci kostky", "Hledam kostky urcene na hrani.",false,true,c1);

        Item i3 = new Item(2,"vzduch","klasicky vzduch",true,false,c2);

        i1.addTag(t1);
        i1.addTag(t3);
        i2.addTag(t2);

        i2.addImage(image);

        ApplicationContext context = new AnnotationConfigApplicationContext("cz.cvut.fit.tjv.nebesluk");
        var clientService = context.getBean("clientService", ClientService.class);
        var itemService = context.getBean("itemService", ItemService.class);
        var imageService = context.getBean("imageService", ImageService.class);
        var tagService = context.getBean("tagService", TagService.class);

        System.out.println("Initial listing:");
        System.out.println("Clients:");
        System.out.println(clientService.readAll());
        System.out.println("Images:");
        System.out.println(imageService.readAll());
        System.out.println("Tags:");
        System.out.println(tagService.readAll());
        System.out.println("Items:");
        System.out.println(itemService.readAll());
        System.out.println("END OF INIT");
        System.out.println();

        System.out.println("Insert pfp:");
        System.out.println(imageService.create(pfp));
        System.out.println("Insert image:");
        System.out.println(imageService.create(image));

        System.out.println("Inserting first user:");
        System.out.println(clientService.create(c1));
        System.out.println("Inserting second user:");
        System.out.println(clientService.create(c2));

        System.out.println("Inserting first tag:");
        System.out.println(tagService.create(t1));
        System.out.println("Inserting second tag:");
        System.out.println(tagService.create(t2));
        System.out.println("Inserting third tag:");
        System.out.println(tagService.create(t3));

        System.out.println("Inserting first posting:");
        System.out.println(itemService.create(i1));
        System.out.println("Inserting second posting:");
        System.out.println(itemService.create(i2));
        System.out.println("Inserting third posting:");
        System.out.println(itemService.create(i3));

        System.out.println("Clients:");
        System.out.println(clientService.readAll());
        System.out.println("Images:");
        System.out.println(imageService.readAll());
        System.out.println("Tags:");
        System.out.println(tagService.readAll());
        System.out.println("Items:");
        System.out.println(itemService.readAll());

        System.out.println("End of value init.");

    }
}
