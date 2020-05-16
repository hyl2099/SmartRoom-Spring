package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.dtos.PictureDto;
import com.smartroom.springServer.repositories.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
public class PictureControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PictureRepository pictureRepository;


    Picture addPictureTest() {
        Picture picture = new Picture("Yuling", new Date(), "C:picture.jpg");
        return this.webTestClient
                .post().uri("/pictures/save")
                .body(BodyInserters.fromValue(picture))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Picture.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreate() {
        Picture picture = new Picture("Yuling", new Date(), "C:picture.jpg");
        Picture picturenew = this.webTestClient
                .post().uri("/pictures/save")
                .body(BodyInserters.fromValue(picture))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Picture.class).returnResult().getResponseBody();
        assertNotNull(picturenew.getId());
        assertNotNull(picturenew.getOwner());
        assertNotNull(picturenew.getUploadTime());
        assertNotNull(picturenew.getPath());
    }

    @Test
    void testReadByID() {
        List< Picture>  picture = this.webTestClient
                .get().uri("/pictures/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList( Picture.class)
                .returnResult().getResponseBody();
        assertEquals("yuling", picture.get(0).getOwner());
    }

    @Test
    void testSellerUpdate() {
        Picture picture = new Picture();
        picture.setOwner("Melisa");
        picture.setUploadTime(new Date());
        picture.setPath("909090990");

        this.webTestClient
                .put().uri("pictures/1")
                .body(BodyInserters.fromValue(picture))
                .exchange()
                .expectStatus().isOk();

        Picture newPicture = this.webTestClient
                .get().uri("pictures/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Picture.class)
                .returnResult().getResponseBody();
        assertEquals("Melisa", newPicture.getOwner());
    }
}
