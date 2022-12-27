package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Clients")
@Table
public class Client implements DomainEntity<Long> {
    // Properties
    @Id
    private Long client_id;

    private String username;

    private String passwordHash;

    private String realName;

    private LocalDateTime dateCreated;

    private LocalDateTime dateLastLogon;

    @OneToOne
    private Image profilePicture;
    // - - - - - //

    public Client(){

    }

    public Client(long _clientID,String _username, String _passwordHash, String _realName, LocalDateTime _dateCreate, LocalDateTime _dateLastLogon,Image _profilePicture){
        client_id = _clientID;
        username = _username;
        passwordHash = _passwordHash;
        realName = _realName;
        dateCreated = (Objects.isNull(_dateCreate)) ? LocalDateTime.now() : _dateCreate;
        dateLastLogon = _dateLastLogon;
        profilePicture = _profilePicture;
    }

    // Get;Set;

    @Override
    public Long getId(){
        return client_id;
    }

    /**
     * Get the client ID
     * @return ID
     */
    public long getClient_id(){
        return client_id;
    }

    /**
     * Set the client id
     * @param _client_id
     */
    public void setClient_id(long _client_id){
        client_id = _client_id;
    }

    /**
     * Get the username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Set the username
     * @param _username username
     * @throws NullPointerException if _username is null
     */
    public void setUsername(String _username){
        username = Objects.requireNonNull(_username);
    }

    /**
     * Get the passwordHash
     * @return passwordHash
     */
    public String getPasswordHash(){
        return passwordHash;
    }

    /**
     * Set the password hash
     * @param _hash hash
     * @throws NullPointerException if _hash is null
     */
    public void setPasswordHash(String _hash){
        passwordHash = Objects.requireNonNull(_hash);
    }

    /**
     * Get the real name
     * @return name
     */
    public String getRealName(){
        return realName;
    }

    /**
     * Set the real name
     * @param _realName name
     * @throws NullPointerException if _realName is not null
     */
    public void setRealName(String _realName){
        realName = Objects.requireNonNull(_realName);
    }

    /**
     * Get creation date
     * @return date
     */
    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    /**
     * Set creation date
     * @param _dateCreated date
     * @throws NullPointerException if _dateCreated is null
     */
    public void setDateCreated(LocalDateTime _dateCreated){
        dateCreated = Objects.requireNonNull(_dateCreated);
    }

    /**
     * Get last logon datetime
     * @return date
     */
    public LocalDateTime getDateLastLogon(){
        return dateLastLogon;
    }

    /**
     * Set last logon datetime
     * @param _dateLastLogon date
     * @throws NullPointerException if _dateLastLogon is null
     */
    public void setDateLastLogon(LocalDateTime _dateLastLogon){
        dateLastLogon = Objects.requireNonNull(_dateLastLogon);
    }

    /**
     * Get profile picture
     * @return profilePicture
     */
    public Image getProfilePicture(){
        return profilePicture;
    }

    /**
     * Set profile picture
     * @param _profilePicture image
     * @throws NullPointerException if _profilePicture is null
     */
    public void setProfilePicture(Image _profilePicture){
        profilePicture = Objects.requireNonNull(_profilePicture);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;

        Client c = (Client)o;

        if(!Objects.equals(this.client_id,c.client_id)) return false;
        if(!Objects.equals(this.username,c.username)) return false;
        if(!Objects.equals(this.realName,c.realName)) return false;
        if(!Objects.equals(this.passwordHash,c.passwordHash)) return false;
        if(!Objects.equals(this.dateCreated,c.dateCreated)) return false;
        if(!Objects.equals(this.dateLastLogon,c.dateLastLogon)) return false;
        return Objects.equals(this.profilePicture, c.profilePicture);
    }

    @Override
    public int hashCode(){
        int hash = client_id != null ? client_id.hashCode() : 0;
        hash = 31 * hash + (username != null ? username.hashCode() : 0);
        hash = 31 * hash + (passwordHash != null ? passwordHash.hashCode() : 0);
        hash = 31 * hash + (realName != null ? realName.hashCode() : 0);
        hash = 31 * hash + (dateCreated != null ? dateCreated.hashCode() : 0);
        hash = 31 * hash + (dateLastLogon != null ? dateLastLogon.hashCode() : 0);
        hash = 31 * hash + (profilePicture != null ? profilePicture.hashCode() : 0);
        return hash;
    }

}
