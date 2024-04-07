package org.oyster_card_task.journey;

import org.oyster_card_task.transport.TransportType;
import org.oyster_card_task.station.Station;

public record Journey(Station startStation, Station endStation, TransportType transportType) {
}
