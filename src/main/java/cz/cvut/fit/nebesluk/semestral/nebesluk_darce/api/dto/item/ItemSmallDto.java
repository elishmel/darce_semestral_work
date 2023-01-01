package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.item;

public class ItemSmallDto {
    private Long itemId;

    private Long author;

    private String name;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
