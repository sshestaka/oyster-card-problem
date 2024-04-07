package org.oyster_card_task.card;

import org.oyster_card_task.journey.Journey;
import org.oyster_card_task.transport.TransportType;
import org.oyster_card_task.exceptions.EmptyZonesException;
import org.oyster_card_task.exceptions.InsufficientFundsOnTheCardException;
import org.oyster_card_task.fare.BusFare;
import org.oyster_card_task.fare.TubeFare;

import java.util.Set;

public class OysterCard {
    private static final double MAXIMUM_FARE = 3.20;
    private static final int ZONE_ONE = 1;
    private static final int ZONE_TWO = 2;
    private double balance;

    public OysterCard(double initialBalance) {
        balance = initialBalance;
    }

    public OysterCard() {
    }

    public void topUp(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void swipeIn() {
        chargeCard(MAXIMUM_FARE);
    }

    public void swipeOut(Journey journey) {
        topUp(calculateFare(journey));
    }

    private void chargeCard(Double fare) {
        //check if the balance is correct
        if (balance < fare) {
            throw new InsufficientFundsOnTheCardException(
                    "Can't charge card! Insufficient funds on the card."
            );
        }
        balance -= fare;
    }

    private double calculateFare(Journey journey) {
        //any bus journey
        if (journey.transportType() == TransportType.BUS) {
            return MAXIMUM_FARE - BusFare.Any_BUS_TRIP.getFare();
        }
        int zonesTraveled = getZonesTraveled(journey); //calculating amount of zones for one journey
        Set<Integer> startStationZones = journey.startStation().getZones();
        Set<Integer> endStationZones = journey.endStation().getZones();
        //one zone journey
        if (zonesTraveled == 1) {
            if ((startStationZones.size() == 1 || endStationZones.size() == 1)
                    && (startStationZones.contains(ZONE_ONE)
                    && endStationZones.contains(ZONE_ONE))
            ) {
                return MAXIMUM_FARE - TubeFare.ZONE_1.getFare();
            }
            return MAXIMUM_FARE - TubeFare.ZONE_OUTSIDE_1.getFare();
        }
        //two zones journey
        if (zonesTraveled == 2) {
            //checking whether one of zones belongs to Zone 1 and to Zone 2?
            if ((startStationZones.contains(ZONE_ONE)
                    && startStationZones.contains(ZONE_TWO))
                    || (endStationZones.contains(ZONE_ONE)
                    && endStationZones.contains(ZONE_TWO))) {
                //if YES, we have to charge the cheaper rate (TWO_ZONES_EXCLUDING_1 = £2.25)
                double minFare = Math.min(TubeFare.TWO_ZONES_INCLUDING_1.getFare(),
                        TubeFare.TWO_ZONES_EXCLUDING_1.getFare());
                return MAXIMUM_FARE - minFare;
            }
            //if one of zones belongs to Zone 1, we must charge TWO_ZONES_INCLUDING_1 = £3.00.
            else if (startStationZones.contains(ZONE_ONE)
                    || endStationZones.contains(ZONE_ONE)) {
                return MAXIMUM_FARE - TubeFare.TWO_ZONES_INCLUDING_1.getFare();
            }
            //If none of the stations belongs to Zone 1, we must charge TWO_ZONES_EXCLUDING_1 = £2.25.
            return MAXIMUM_FARE - TubeFare.TWO_ZONES_EXCLUDING_1.getFare();
        }
        //three zones journey
        if (zonesTraveled == 3) {
            return MAXIMUM_FARE - TubeFare.THREE_ZONES.getFare();
        }
        return MAXIMUM_FARE; //in any other cases
    }

    private int getZonesTraveled(Journey journey) {
        Set<Integer> startZones = journey.startStation().getZones();
        Set<Integer> endZones = journey.endStation().getZones();
        // Invalid stations for Tube journey
        if (startZones.isEmpty() || endZones.isEmpty()) {
            throw new EmptyZonesException("Journey zones can't be empty or null");
        }
        //count the number of zones
        int minZones = Integer.MAX_VALUE;
        for (Integer startZone : startZones) {
            for (Integer endZone : endZones) {
                int zones = Math.abs(startZone - endZone) + 1; // Include both start and end zones
                minZones = Math.min(minZones, zones);
            }
        }
        return minZones;
    }
}
