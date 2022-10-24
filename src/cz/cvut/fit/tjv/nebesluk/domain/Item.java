package cz.cvut.fit.tjv.nebesluk.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing offer/request item and table
 */
public class Item implements DomainEntity<Long> {
    // Properties
    /**
     * ID of the item
     */
    private Long item_id;
    /**
     * Name of the offer
     */
    private String name;
    /**
     * Description of the item
     */
    private String description;
    /**
     * Flag indicating if item is offer or request
     */
    private boolean isOffer;
    /**
     * Flag indicating whether the Item is currently active
     * eg. if it should be displayed to customers
     */
    private boolean isActive;

    private Set<Tag> tags = new HashSet<>();
    private Set<Image> images = new HashSet<>();

    // - - - - - //
    // Get;Set;

    @Override
    public Long getId(){
        return item_id;
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

    /**
     * Add tag
     * @param tag tag
     * @return True on success, false otherwise
     */
    public boolean addTag(Tag tag){
        return tags.add(Objects.requireNonNull(tag));
    }

    /**
     * Remove tag
     * @param tag tag
     * @return True on success, false otherwise
     */
    public boolean removeTag(Tag tag){
        return tags.remove(Objects.requireNonNull(tag));
    }

    public boolean addImage(Image image){
        return images.add(Objects.requireNonNull(image));
    }

    public boolean removeImage(Image image){
        return images.remove(Objects.requireNonNull(image));
    }

    // - - - - - //
}
