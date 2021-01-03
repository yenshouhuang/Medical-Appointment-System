package com.thomas;

import java.util.*;

public class Appointment {
    private Patient patient;
    private Illness illness;
    private Date date;
//    public String address;

    public Appointment( Patient patient, Illness illness, Date date) {
        this.patient = patient;
        this.illness = illness;
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient +
                ", illness=" + illness +
                ", date=" + date +
                '}';
    }
}
