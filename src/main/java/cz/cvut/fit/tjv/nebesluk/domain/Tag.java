package cz.cvut.fit.tjv.nebesluk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class representing the Tag object and table
 */
@Entity
@Table(name = "tbl_tag")
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
}
