package cz.cvut.fit.tjv.nebesluk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URI;
import java.util.Objects;

/**
 * Class representing the Image object and table
 */
@Entity
@Table(name = "tbl_image")
public class Image implements DomainEntity<Long>{
    // Properties

    @Id
    private Long image_id;

    private URI url;

    // - - - - - //
    public Image(){

    }

    public Image(long _imageID, URI _uri){
        image_id = _imageID;
        url = _uri;
    }

    // Get;Set;

    @Override
    public Long getId(){
        return image_id;
    }

    /**
     * Set image_id
     * @param _image_id ID of the image
     */
    public void setImage_id(long _image_id){
        image_id = _image_id;
    }

    /**
     * Get the image ID
     * @return long - ID of the image
     */
    public long getImage_id(){
        return image_id;
    }

    /**
     * Set the image URL
     * @param _url URL of the image
     * @throws NullPointerException if _url is null
     */
    public void setUrl(URI _url){
        url = Objects.requireNonNull(_url);
    }

    /**
     * Get the image URL
     * @return URI - URL of the image
     */
    public URI getUrl(){
        return url;
    }

    // - - - - - //
}
