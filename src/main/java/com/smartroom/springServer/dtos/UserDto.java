package com.smartroom.springServer.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.smartroom.springServer.documents.User;

import java.time.LocalDateTime;
import java.util.Arrays;

@JsonInclude(Include.NON_NULL)
public class UserDto extends UserMinimumDto {

    private String email;

    private String dni;

    private String address;

    private Boolean active;

    private LocalDateTime registrationDate;

    public UserDto() {
        // Empty for framework
    }

    public UserDto(User user) {
        super(user.getMobile(), user.getUsername(), user.getRoles());
        this.email = user.getEmail();
        this.dni = user.getDni();
        this.address = user.getAddress();
        this.active = user.isActive();
        this.registrationDate = user.getRegistrationDate();
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public Boolean isActive() {
        return active;
    }

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "mobile='" + this.getMobile() + '\'' +
                ", username='" + this.getUsername() + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", roles=" + Arrays.toString(this.getRoles()) +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
