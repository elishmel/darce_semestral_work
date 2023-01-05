package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    TagService tagService;

    public TagController(TagService tagService_){
        tagService=tagService_;
    }

    @GetMapping
    public Collection<Tag> GetAll(){
        return tagService.ReadAll();
    }

    @PostMapping
    public Tag Create(@RequestBody Tag tag){
        try{
            return tagService.Create(tag);
        } catch (EntityAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public Tag Update(@RequestBody Tag tag, @PathVariable String id){
        try{
            return tagService.Update(tag);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable String id){
        try{
            tagService.DeleteById(id);
        } catch (EntityNotExistsException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
