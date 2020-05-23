package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.smartroom.springServer.documents.User;

import java.time.LocalDateTime;
import java.util.Arrays;

@JsonInclude(Include.NON_NULL)
public class UserDto extends UserMinimumDto {

    private String email;

    private Boolean active;

    public UserDto() {
        // Empty for framework
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.active = user.isActive();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "mobile='" + this.getMobile() + '\'' +
                ", username='" + this.getUsername() + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", roles=" + Arrays.toString(this.getRoles()) +
                '}';
    }
}
