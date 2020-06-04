package com.smartroom.springServer.repositories;

import com.smartroom.springServer.documents.Temperature;
import org.springframework.data.repository.CrudRepository;

public interface TemperatureRepository extends CrudRepository<Temperature, Long> {
}
