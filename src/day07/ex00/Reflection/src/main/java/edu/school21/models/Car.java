package edu.school21.models;

import java.util.StringJoiner;

public class Car {
    private String model;
    private String color;
    private int crashesCount;

    public Car() {
    }

    public Car(String model, String color, int crashesCount) {
        this.model = model;
        this.color = color;
        this.crashesCount = crashesCount;
    }

    public void crashed(int n) {
        crashesCount += n;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[","]").
                add("model="+model +"")
                .add("color=" + color + "")
                .add("crashesCount=" + crashesCount +"").toString();
    }
}
