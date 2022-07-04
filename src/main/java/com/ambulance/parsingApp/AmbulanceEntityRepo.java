package com.ambulance.parsingApp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmbulanceEntityRepo extends CrudRepository<AmbulanceEntity, String> {
    List<AmbulanceEntity> findByCallDate(java.util.Date callDate);
}
