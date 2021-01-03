package com.thomas;

import java.sql.Time;

public class Patient extends Human  {

    private Illness illness;

    Patient(String name, Integer age, Integer id) {
        super(name, age);
        this.id = id;
    }

    Patient(String name, Integer age) {
        super(name, age);
    }

    Patient() {

    }

    public Patient(String name, Integer age, Integer id, String illness) {
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
