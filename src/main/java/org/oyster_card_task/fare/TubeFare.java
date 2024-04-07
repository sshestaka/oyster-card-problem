package org.oyster_card_task.fare;

public enum TubeFare {
    ZONE_1(2.50), // Anywhere in Zone 1
    ZONE_OUTSIDE_1(2.00), // Any one zone outside Zone 1
    TWO_ZONES_INCLUDING_1(3.00), // Any two zones including Zone 1
    TWO_ZONES_EXCLUDING_1(2.25), // Any two zones excluding Zone 1
    THREE_ZONES(3.20); // Any three zones

    private final double fare;

    TubeFare(double fare) {
        this.fare = fare;
    }

    public double getFare() {
        return fare;
    }
}
