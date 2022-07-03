package com.ambulance.parsingApp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

//Класс со структурой данных, которые будут записываться в таблицу бд
@Entity
public class AmbulanceEntity {
    @Id
    private String id;

    private java.util.Date callDate;
    private Integer age;
    private String cause;
    private String diagnose;
    private String destination;
    private java.util.Date timeResponse;
    private java.util.Date timeArrive;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Date getCallDate() { return callDate; }
    public void setCallDate(Date callDate) { this.callDate = callDate; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCause() { return cause; }
    public void setCause(String cause) { this.cause = cause; }

    public String getDiagnose() { return diagnose; }
    public void setDiagnose(String diagnose) { this.diagnose = diagnose; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Date getTimeResponse() { return timeResponse; }
    public void setTimeResponse(Date timeResponse) { this.timeResponse = timeResponse; }

    public Date getTimeArrive() { return timeArrive; }
    public void setTimeArrive(Date timeArrive) { this.timeArrive = timeArrive; }

    public AmbulanceEntity() {
    }

    public AmbulanceEntity(String id, java.util.Date callDate, Integer age, String cause, String diagnose, String destination , java.util.Date timeResponse, java.util.Date timeArrive){
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
