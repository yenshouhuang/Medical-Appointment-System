package com.thomas;

public class Human {
    public String name;
    public Integer age;
    protected Integer id;

    Human(){

    }

    Human(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    Human (String name, Integer age, Integer id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
}
