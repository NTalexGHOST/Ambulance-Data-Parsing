package com.ambulance.parsingApp;

import org.springframework.data.repository.CrudRepository;

public interface AmbulanceEntityRepo extends CrudRepository<AmbulanceEntity, String> {
    int countAllByCallDateBetween(java.util.Date startDate, java.util.Date endDate);
}
