package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URI;
import java.util.Objects;

/**
 * Class representing the Image object and table
 */
@Entity
@Table
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!image_id.equals(image.image_id)) return false;
        return url.equals(image.url);
    }

    @Override
    public int hashCode() {
        int result = image_id.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
