package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
