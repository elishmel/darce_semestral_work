package cz.cvut.fit.tjv.nebesluk.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing offer/request item and table
 */
public class Item implements DomainEntity<Long> {
    // Properties
    private Long item_id;
    private String name;
    private String description;
    private boolean isOffer;
    private boolean isActive;
    private Client author;

    private Set<Tag> tags = new HashSet<>();
    private Set<Image> images = new HashSet<>();

    // - - - - - //
    // Get;Set;

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
        return isOffer;
    }

    /**
     * Set new isOffer state
     * @param _isOffer New isOffer state
     */
    public void setOffer(boolean _isOffer){
        isOffer = _isOffer;
    }

    /**
     * Get the isActive flag
     * @return Bool - the isActive flag
     */
    public boolean getActive(){
        return isActive;
    }

    /**
     * Set new isActive flag
     * @param _isActive New isActive value
     */
    public void setActive(boolean _isActive){
        isActive = _isActive;
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

    // - - - - - //
}
