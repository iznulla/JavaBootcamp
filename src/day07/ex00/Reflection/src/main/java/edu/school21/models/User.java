package edu.school21.models;

import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String location;

    public User() {}
    public User(String name, String lastName, int age, String location) {
        this.firstName = name;
        this.lastName = lastName;
        this.age= age;
        this.location = location;
    }

    public void relocation(String newLocation) {
        location = newLocation;
    }

    public int birthday() {
        return age += 1;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[","]").
                add("firstName="+firstName +"")
                .add("lastName=" + lastName + "")
                .add("age=" + age +"")
                .add("location=" + location).toString();
    }
}
