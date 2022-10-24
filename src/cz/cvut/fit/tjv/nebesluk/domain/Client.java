package cz.cvut.fit.tjv.nebesluk.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Client implements DomainEntity<Long> {
    // Properties

    /**
     * Client ID - table primary key
     */
    private Long client_id;
    /**
     * Username
     */
    private String username;
    /**
     * Hashed password
     */
    private String passwordHash;
    /**
     * Real name of the user
     */
    private String realName;
    /**
     * Date & time on which was the user created
     */
    private LocalDateTime dateCreated;
    /**
     * Date & time on which the user last logged in
     */
    private LocalDateTime dateLastLogon;
    /**
     * Profile picture
     */
    private Image profilePicture;
    // - - - - - //
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
}
