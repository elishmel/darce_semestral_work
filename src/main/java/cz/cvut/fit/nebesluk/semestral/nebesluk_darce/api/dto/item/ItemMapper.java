package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Image;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Item;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Tag;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityNotExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.TagService;

public class ItemMapper {

    ClientService clientService;
    TagService tagService;
    ImageService imageService;

    public ItemMapper(ClientService clientService_, TagService tagService_, ImageService imageService_){
        clientService = clientService_;
        tagService = tagService_;
        imageService = imageService_;
    }

    public Item toEntity(ItemDto dto){
        var entity = new Item();
        entity.setItem_id(dto.getItemId());
        entity.setAuthor(clientService.ReadById(dto.getAuthorId()).orElseThrow(EntityNotExistsException::new));
        // TODO: Finish
        return entity;
    }



    public Item toEntity(NewItemDto dto){
        var entity = new Item();
        entity.setAuthor(clientService.ReadById(dto.getAuthorId()).orElseThrow(EntityNotExistsException::new));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if(dto.getTags() != null){
            dto.getTags().forEach(
                    tag -> entity.addTag(tagService.ReadById(tag).orElseThrow(EntityNotExistsException::new)));
        }
        if(dto.getImages() != null){
            dto.getImages().forEach(image -> entity.addImage(imageService.ReadById(image).orElseThrow(EntityNotExistsException::new)));
        }
        return entity;
    }

    public ItemSmallDto toSmallDto(Item entity){
        var dto = new ItemSmallDto();
        dto.setItemId(entity.getItem_id());
        dto.setAuthor(entity.getAuthor().getId());
        dto.setName(entity.getName());
        dto.setOffer(entity.getOffer());
        Long[] ids = new Long[entity.getImages().size()];
        int iterator = 0;
        for (var item :
                entity.getImages()) {
            ids[iterator++] = item.getImage_id();
        }
        dto.setImages(ids);
        return dto;
    }

    public ItemDto toDto(Item entity){
        var dto = new ItemDto();
        dto.setItemId(entity.getItem_id());
        dto.setAuthorId(entity.getAuthor().getId());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setOffer(entity.getOffer());
        dto.setTags(entity.getTag().parallelStream().map(Tag::getTag).toList());
        dto.setImages(entity.getImages().parallelStream().map(Image::getImage_id).toList());
        return dto;
    }

}
