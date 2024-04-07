package org.oyster_card_task.fare;

public enum BusFare {
    Any_BUS_TRIP(1.80);

    private final double fare;

    BusFare(double fare) {
        this.fare = fare;
    }

    public double getFare() {
        return fare;
    }
}
