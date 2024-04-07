package org.oyster_card_task.station;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Station {
    HOLBORN(1),
    CHELSEA(),
    EARLS_COURT(1, 2),
    WIMBLEDON(3),
    HAMMERSMITH(2);

    private final Set<Integer> zones;

    Station(Integer... zones) {
        this.zones = new HashSet<>(Arrays.asList(zones));
    }

    public Set<Integer> getZones() {
        return zones;
    }
}
