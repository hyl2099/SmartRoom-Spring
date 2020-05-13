package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
}
