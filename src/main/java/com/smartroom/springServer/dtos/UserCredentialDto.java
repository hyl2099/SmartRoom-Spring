package com.smartroom.springServer.dtos;

import javax.validation.constraints.NotNull;

public class UserCredentialDto {

    @NotNull
    private String email;

    @NotNull
    private String newPassword;

    public UserCredentialDto() {
        this("", "");
    }

    public UserCredentialDto(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getMobile() {
        return email;
    }

    public void setMobile(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
