package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class representing the Tag object and table
 */
@Entity(name = "Tags")
@Table
public class Tag implements DomainEntity<String>{
    /**
     * ID and value of the Tag
     */
    @Id
    private String tag;

    public Tag(){

    }

    public Tag(String _tag){
        tag = _tag ;
    }

    @Override
    public String getId(){
        return tag;
    }

    /**
     * Get the Tag value
     * @return String - Tag value
     */
    public String getTag(){
        return tag;
    }

    /**
     * Set the Tag value
     * @param _value New Tag value
     * @throws NullPointerException if _value is null
     */
    public void setTag(String _value){
        tag = Objects.requireNonNull(_value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag1 = (Tag) o;

        return tag.equals(tag1.tag);
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}
