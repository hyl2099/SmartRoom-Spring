package com.smartroom.springServer.dtos;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Arrays;

public class UserMinimumDto {

    @NotNull
    @Pattern(regexp = com.smartroom.springServer.dtos.validations.Pattern.NINE_DIGITS)
    private String email;

    @NotNull
    private String username;


    public UserMinimumDto() {
        this("000000000", "");
    }

    public UserMinimumDto(String email, String username) {
        this.email = email;
        this.username = username;
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


    @Override
    public String toString() {
        return "UserMinimumDto [email=" + email + ", username=" + username + ", roles=" + "]";
    }
}
