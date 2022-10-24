package cz.cvut.fit.tjv.nebesluk.domain;

import java.time.LocalDateTime;

public class Client implements DomainEntity<Long> {
    // Properties
    private Long client_id;
    private String username;
    private String passwordHash;
    private String realName;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastLogon;
    private Image profilePicture;
    // - - - - - //
    // Get;Set;

    @Override
    public Long getId(){
        return client_id;
    }

    public long getClient_id(){
        return client_id;
    }

    public void setClient_id(long _client_id){
        client_id = _client_id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String _username){
        username = _username;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String _hash){
        passwordHash = _hash;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(String _realName){
        realName = _realName;
    }

    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime _dateCreated){
        dateCreated = _dateCreated;
    }

    public LocalDateTime getDateLastLogon(){
        return dateLastLogon;
    }

    public void setDateLastLogon(LocalDateTime _dateLastLogon){
        dateLastLogon = _dateLastLogon;
    }

    public Image getProfilePicture(){
        return profilePicture;
    }

    public void setProfilePicture(Image _profilePicture){
        profilePicture = _profilePicture;
    }
}
