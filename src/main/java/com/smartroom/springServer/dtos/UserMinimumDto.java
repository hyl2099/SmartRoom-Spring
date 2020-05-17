package com.smartroom.springServer.dtos;



import com.smartroom.springServer.documents.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Arrays;

public class UserMinimumDto {

    @NotNull
    @Pattern(regexp = com.smartroom.springServer.dtos.validations.Pattern.NINE_DIGITS)
    private String email;

    @NotNull
    private String username;

    @NotNull
    private Role[] roles;

    public UserMinimumDto() {
        this("000000000", "", new Role[]{});
    }

    public UserMinimumDto(String email, String username, Role[] roles) {
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    public String getMobile() {
        return email;
    }

    public void setMobile(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserMinimumDto [email=" + email + ", username=" + username + ", roles=" + Arrays.toString(roles) + "]";
    }
}
