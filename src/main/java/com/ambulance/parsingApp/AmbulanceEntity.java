package com.ambulance.parsingApp;

//Класс со структурой данных, которые будут записываться в таблицу бд
public class AmbulanceEntity {
    public final String id;

    public final java.util.Date callDate;

    public final Integer age;

    public final String cause;

    public final String diagnose;

    public final String destination;

    public final java.util.Date timeResponse;

    public final java.util.Date timeArrive;

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

    public void checkDataIntegrity() throws Exception {
        Exception emptyornull = new Exception("Can't use empty string");
        if (id==null || id.isEmpty())
            throw emptyornull;
        if (callDate==null)
            throw emptyornull;
        if (age==null)
            throw emptyornull;
        if (cause==null || cause.isEmpty())
            throw emptyornull;
        if (diagnose==null || diagnose.isEmpty())
            throw emptyornull;
        if (destination==null || destination.isEmpty())
            throw emptyornull;
        if (timeResponse==null)
            throw emptyornull;
        if (timeArrive==null)
            throw emptyornull;
    }
}
