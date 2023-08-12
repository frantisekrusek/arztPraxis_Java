package model.appointment;

import model.person.patient.Patient;

import java.time.ZonedDateTime;

public class Appointment {
    //name wird verwendet, um das Zeitfenster näher zu beschreiben.
    private String name;
    private ZonedDateTime date;
    private boolean taken;
    private Patient patient;
    private int id;

    //ctr
    public Appointment(String name, ZonedDateTime date, boolean taken) {
        this.name = name;
        this.date = date;
        this.taken = taken;
    }

    //GETTER, SETTER
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public ZonedDateTime getDate() {
        return date;
    }
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}//end class
