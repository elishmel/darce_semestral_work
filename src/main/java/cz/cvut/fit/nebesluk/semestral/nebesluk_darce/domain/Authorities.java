package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Authorities implements Serializable {
    @Id
    private String username;

    @Id
    private String authority;

    public Authorities(){}

    public Authorities(String username_,String authority_){
        username = username_;
        authority = authority_;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
