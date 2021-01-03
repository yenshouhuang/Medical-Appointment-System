package com.thomas;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Human{

    private List<Skill> skills;
    private List<Appointment> appointments;

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    Doctor(String name, Integer age, Integer id, List<Skill> skills) {
        super(name, age, id);
        this.skills = skills;
        this.appointments = new ArrayList<>();
//        System.out.println("name: " + name + ", age: " + age + ", id: " + id);
    }

    public void learn (Skill skill) {
        this.skills.add(skill);
    }

    public void makeAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        System.out.println("Appointment added");
        System.out.println(appointment);
    }
}
