package com.smartroom.springServer.documents;

import javax.persistence.Entity;

public enum Role {
    ADMIN, MANAGER, OPERATOR, CUSTOMER, AUTHENTICATED;
    public String roleName() {
        return "ROLE_" + this.toString();
    }
}