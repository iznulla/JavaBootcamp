package edu.school21.models;

import java.util.StringJoiner;

public class Col {
    private double name;

    public Col(){}
    public Col(double name) {
        this.name = name;
    }

    public double rr(double a, double b) {
        return a + b;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Col.class.getSimpleName() + "[","]").
                add("name="+name +"").toString();
    }
}
