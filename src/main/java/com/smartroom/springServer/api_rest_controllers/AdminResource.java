package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(com.smartroom.springServer.api_rest_controllers.AdminResource.ADMINS)
public class AdminResource {

    public static final String ADMINS = "/admins";
    public static final String DB = "/db";

    private AdminController adminController;

    @Autowired
    public AdminResource(AdminController adminController) {
        this.adminController = adminController;
    }

    @DeleteMapping(value = DB)
    public void deleteDb() {
        this.adminController.deleteDb();
    }

    @PostMapping(value = DB)
    public void seedDb() {
        this.adminController.seedDataBaseJava();
    }
}
