package com.smartroom.springServer.data_services;

import com.smartroom.springServer.documents.*;
import com.smartroom.springServer.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Service

public class DatabaseSeederService {
    public static final String VARIOUS_CODE = "1";
    public static final String VARIOUS_NAME = "Various";
    public static final String CUSTOMER_POINTS_CODE = "0";
    public static final String CUSTOMER_POINTS_NAME = "Customer points";

    @Value("${miw.admin.mobile}")
    private String mobile;
    @Value("${miw.admin.username}")
    private String username;
    @Value("${miw.admin.password}")
    private String password;

    private DoorbellRepository doorbellRepository;
    private PictureRepository pictureRepository;
    private Environment environment;

    @Autowired
    public DatabaseSeederService(DoorbellRepository doorbellRepository,PictureRepository pictureRepository,Environment environment) {
        this.doorbellRepository = doorbellRepository;
        this.pictureRepository = pictureRepository;
        this.environment = environment;
    }

    @PostConstruct
    public void constructor() {
        String[] profiles = this.environment.getActiveProfiles();
        if (Arrays.asList(profiles).contains("dev")) {
            this.deleteAllAndInitializeAndSeedDataBase();
        } else if (Arrays.asList(profiles).contains("prod")) {
            this.initialize();
        }
    }

    private void initialize() {
    }

    public void deleteAllAndInitialize() {
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
        // Delete Repositories -----------------------------------------------------
        this.pictureRepository.deleteAll();
        this.doorbellRepository.deleteAll();
        this.initialize();
    }

    public void deleteAllAndInitializeAndSeedDataBase() {
        this.deleteAllAndInitialize();
        this.seedDataBaseJava();
    }

    public void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
        Role[] allRoles = {Role.ADMIN, Role.MANAGER, Role.OPERATOR};

        Picture[] pictures = {
                new Picture("00000", new Date()),
                new Picture("11111",new Date()),
                new Picture("22222", new Date())
        };
        this.pictureRepository.saveAll(Arrays.asList(pictures));
        LogManager.getLogger(this.getClass()).warn("        ------- pictures");

        Doorbell[] doorbells = {
                new Doorbell(new Date(), new Picture("00000", new Date())),
                new Doorbell(new Date(),new Picture("00000", new Date()))
        };
        this.doorbellRepository.saveAll(Arrays.asList(doorbells));
        LogManager.getLogger(this.getClass()).warn("        ------- doorbells");
    }


}
