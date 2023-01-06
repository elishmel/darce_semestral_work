package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing offer/request item and table
 */
@Entity(name = "Items")
@Table
public class Item implements DomainEntity<Long> {
    // Properties
    @Id
    private Long item_id;
    private String name;
    @Column(length = 800)
    private String description;
    private boolean offer;
    private boolean active;
    @ManyToOne
    private Client author;
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();
    @ManyToMany
    private Set<Image> images = new HashSet<>();

    @Nullable
    @ManyToOne
    private Client receiver;

    // - - - - - //

    public Item(){}

    public Item(long _itemID, String _name, String _description,boolean _isOffer, boolean _isActive, Client _author){
        item_id = _itemID;
        name = _name;
        description = _description;
        offer = _isOffer;
        active = _isActive;
        author = _author;
    }

    // Get;Set;


    @Nullable
    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(@Nullable Client receiver) {
        this.receiver = receiver;
    }

    @Override
    public Long getId(){
        return item_id;
    }

    public Client getAuthor() {
        return author;
    }

    public void setAuthor(Client author) {
        this.author = author;
    }

    /**
     * Get the item ID
     * @return long - Item ID
     */
    public long getItem_id(){
        return item_id;
    }

    /**
     * Sets new item ID
     * @param _itemID - new item ID
     */
    public void setItem_id(long _itemID){
        item_id = _itemID;
    }

    /**
     * Get the name of the item
     * @return String - name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Set new name for the item
     * @param _name New item name
     * @throws NullPointerException if _name is null
     */
    public void setName(String _name){
        name = Objects.requireNonNull(_name);
    }

    /**
     * Get the item description
     * @return String item description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Set new item description
     * @param _description New item description
     * @throws NullPointerException if _description is null
     */
    public void setDescription(String _description){
        description = Objects.requireNonNull(_description);
    }

    /**
     * Get the isOffer state
     * @return Bool - isOffer state of the item
     */
    public boolean getOffer(){
        return offer;
    }

    /**
     * Set new isOffer state
     * @param _isOffer New isOffer state
     */
    public void setOffer(boolean _isOffer){
        offer = _isOffer;
    }

    /**
     * Get the isActive flag
     * @return Bool - the isActive flag
     */
    public boolean getActive(){
        return active;
    }

    /**
     * Set new isActive flag
     * @param _isActive New isActive value
     */
    public void setActive(boolean _isActive){
        active = _isActive;
    }

    public boolean hasTag(Tag tag){
        return tags.contains(tag);
    }

    /**
     * Add tag
     * @param tag tag
     * @return True on success, false otherwise
     * @throws NullPointerException if tag is null
     */
    public boolean addTag(Tag tag){
        return tags.add(Objects.requireNonNull(tag));
    }

    /**
     * Remove tag
     * @param tag tag
     * @return True on success, false otherwise
     * @throws NullPointerException if tag is null
     */
    public boolean removeTag(Tag tag){
        return tags.remove(Objects.requireNonNull(tag));
    }

    public Collection<Tag> getTag(){return tags;}

    /**
     * Add image to this item
     * @param image image
     * @return True on success, false otherwise
     * @throws NullPointerException if image is null
     */
    public boolean addImage(Image image){
        return images.add(Objects.requireNonNull(image));
    }

    /**
     * Remove image from this item
     * @param image image
     * @return True on success, false otherwise
     * @throws NullPointerException if image is null
     */
    public boolean removeImage(Image image){
        return images.remove(Objects.requireNonNull(image));
    }

    public Collection<Image> getImages(){
        return images;
    }

    // - - - - - //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (offer != item.offer) return false;
        if (active != item.active) return false;
        if (!item_id.equals(item.item_id)) return false;
        if (!name.equals(item.name)) return false;
        if (!description.equals(item.description)) return false;
        if (!author.equals(item.author)) return false;
        if (!tags.equals(item.tags)) return false;
        return images.equals(item.images);
    }

    @Override
    public int hashCode() {
        int result = item_id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (offer ? 1 : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + author.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + images.hashCode();
        return result;
    }
}
