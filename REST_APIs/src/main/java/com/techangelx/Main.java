package com.techangelx;

public class Main {
    public static void main(String[] args) {

        String city = "houston";
        int votesThreshold = 200;

        String result = FinestFoodOutlet.finestFoodOutlet(city, votesThreshold);

        System.out.println("Best outlet: " + result);
    }
}
