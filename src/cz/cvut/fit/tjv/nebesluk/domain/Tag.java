package cz.cvut.fit.tjv.nebesluk.domain;

import java.util.Objects;

/**
 * Class representing the Tag object and table
 */
public class Tag implements DomainEntity<String>{
    /**
     * ID and value of the Tag
     */
    private String value;

    @Override
    public String getId(){
        return value;
    }

    /**
     * Get the Tag value
     * @return String - Tag value
     */
    public String getValue(){
        return value;
    }

    /**
     * Set the Tag value
     * @param _value New Tag value
     * @throws NullPointerException if _value is null
     */
    public void setValue(String _value){
        value = Objects.requireNonNull(_value);
    }
}
