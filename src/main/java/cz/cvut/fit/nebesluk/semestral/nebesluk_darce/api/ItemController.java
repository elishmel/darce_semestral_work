package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item.*;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ItemService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/items")
public class ItemController{

    private ItemService itemService;
    private ClientService clientService;
    private final ItemMapper mapper;

    public ItemController(ItemService itemService_, ClientService clientService_, ImageService imageService_, TagService tagService_){
        itemService = itemService_;
        clientService = clientService_;
        mapper = new ItemMapper(clientService_,tagService_,imageService_);
    }

    @PostMapping("/offer")
    public ItemDto Offer(@RequestBody NewItemDto dto) {
        try{
            return mapper.toDto(itemService.OfferItem(mapper.toEntity(dto)));
        } catch (EntityAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/offer")
    public Collection<ItemSmallDto> GetOffers(){
        return itemService.GetAllOffers().stream().map(mapper::toSmallDto).toList();
    }

    @PostMapping("/request")
    public ItemDto Request(@RequestBody NewItemDto dto){
        try {
            return mapper.toDto(itemService.RequestItem(mapper.toEntity(dto)));
        } catch (EntityAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/request")
    public Collection<ItemSmallDto> GetRequests(){
        return itemService.GetAllRequests().stream().map(mapper::toSmallDto).toList();
    }

    @GetMapping
    public Collection<ItemSmallDto> GetAll() {
        return itemService.ReadAll().stream().map(mapper::toSmallDto).toList();
    }

    @GetMapping("/{id}")
    public ItemDto ReadById(@PathVariable Long id){
        try {
            return mapper.toDto(itemService.ReadById(id).orElseThrow(EntityNotExistsException::new));
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ItemDto Update(Long id, NewItemDto entity) {
        try{
            Item e = mapper.toEntity(entity);
            e.setItem_id(id);
            return mapper.toDto(itemService.Update(e));
        } catch (EntityAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void Delete(Long id) {
        try{
            itemService.DeleteById(id);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/{term}")
    public Collection<ItemSmallDto> GetAllWithTerm(@PathVariable String term){
        return itemService.GetAllWithTermInName(term).stream().map(mapper::toSmallDto).toList();
    }

    @GetMapping("/author/{authorId}")
    public Collection<ItemSmallDto> GetAllFromAuthor(@PathVariable Long authorId){
        return itemService.GetAllFromAuthor(authorId).stream().map(mapper::toSmallDto).toList();
    }

    @GetMapping("/tags")
    public Collection<ItemSmallDto> GetAllWithTags(@RequestBody HashSet<String> tags){
        return itemService.GetAllWithTags(tags).stream().map(mapper::toSmallDto).toList();
    }
}