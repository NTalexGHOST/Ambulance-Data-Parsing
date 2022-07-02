package com.ambulance.parsingApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AmbulanceEntity {
    @Id
    @Column
    private String id;

    @Column
    public java.util.Date callDate;

    @Column
    public Integer age;

    @Column
    public String cause;

    @Column
    public String diagnose;

    @Column
    public String destination;

    @Column
    public java.util.Date timeResponse;

    @Column
    public java.util.Date timeArrive;

    public AmbulanceEntity(String id, java.util.Date callDate, Integer age, String cause, String diagnose, String destination ,java.util.Date timeResponse, java.util.Date timeArrive){
        this.id = id;
        this.callDate = callDate;
        this.age = age;
        this.cause = cause;
        this.diagnose = diagnose;
        this.destination = destination;
        this.timeResponse = timeResponse;
        this.timeArrive = timeArrive;
    }
}
