package com.thomas;

import java.util.HashMap;
import java.util.Map;

public class PatientRecords {
//    ####
    public static Map<Integer, Patient> records = new HashMap<>();

    public static void addPatient(Patient patient){
        Integer id = patient.getId();
        if (records.containsKey(id)){
            System.out.println("id already exist");
        }
        else{
            records.put(id, patient);
            System.out.println("Patient created: " + patient);
        }
    }
}
