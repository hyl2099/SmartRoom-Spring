package com.smartroom.springServer.documents;

import javax.persistence.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.UUID;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String mobile;
    private String username;
    private String password;
    private Boolean active;

    public User() {
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        if (password == null) {
            this.password = UUID.randomUUID().toString();
        } else {
            this.password = new BCryptPasswordEncoder().encode(password);
        }
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return this.mobile.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && mobile.equals(((User) obj).mobile);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                '}';
    }
}
