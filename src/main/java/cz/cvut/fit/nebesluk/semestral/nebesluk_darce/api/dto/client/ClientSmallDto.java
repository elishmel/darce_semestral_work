package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client;

import java.net.URI;

public class ClientSmallDto {

    private Long client_id;

    private String username;

    private URI profilePicture;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public URI getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(URI profilePicture) {
        this.profilePicture = profilePicture;
    }
}
