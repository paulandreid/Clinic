package com.Model;

import java.util.Random;

public enum Consultation {
    consultation(30, 50), treatment(40, 35), prescription(20, 20);


    private final int time;
    private final int cost;

    Consultation(int time, int cost) {
        this.time = time;
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

    public static Consultation getRandomConsultation() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
