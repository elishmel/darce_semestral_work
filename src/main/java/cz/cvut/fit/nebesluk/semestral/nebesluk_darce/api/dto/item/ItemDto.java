package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item;

import java.util.Collection;

public class ItemDto {

    private Long itemId;

    private Long authorId;

    private String name;

    private String description;

    private boolean offer;

    private Collection<String> tags;

    private Collection<Long> images;


    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOffer() {
        return offer;
    }

    public void setOffer(boolean offer) {
        this.offer = offer;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public Collection<Long> getImages() {
        return images;
    }

    public void setImages(Collection<Long> images) {
        this.images = images;
    }
}