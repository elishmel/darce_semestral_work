package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;

    public User(String username_, String password_){
        username = username_;
        password = password_;
        enabled = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
