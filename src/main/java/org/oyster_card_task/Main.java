package org.oyster_card_task;

import org.oyster_card_task.card.OysterCard;
import org.oyster_card_task.journey.Journey;
import org.oyster_card_task.station.Station;
import org.oyster_card_task.transport.TransportType;

public class Main {
    public static void main(String[] args) {
        OysterCard card = new OysterCard(0);
        card.topUp(30);

        Station holborn = Station.HOLBORN;
        Station chelsea = Station.CHELSEA;
        Station earlsCourt = Station.EARLS_COURT;
        Station hammersmith = Station.HAMMERSMITH;

        TransportType bus = TransportType.BUS;
        TransportType tube = TransportType.TUBE;

        System.out.println("Start Journey-1 from Holborn to Earl’s Court by Tube:");
        Journey journey1 = new Journey(holborn, earlsCourt,tube);
        double cardBalanceBeforeJourney = card.getBalance();
        System.out.printf("Card balance before start = %.2f£ \n", cardBalanceBeforeJourney);
        card.swipeIn();
        System.out.printf("Card balance after start = %.2f£ \n", card.getBalance());
        card.swipeOut(journey1);
        System.out.printf("Card balance after finish = %.2f£ \n", card.getBalance());
        double cardBalanceAfterJourney = card.getBalance();
        System.out.printf("Journey-1 fare is = %.2f£ \n\n",
                cardBalanceBeforeJourney - cardBalanceAfterJourney);

        System.out.println("Start Journey-2 from Earl’s Court to Chelsea by Bus 328:");
        Journey journey2 = new Journey(earlsCourt, chelsea, bus);
        cardBalanceBeforeJourney = card.getBalance();
        System.out.printf("Card balance before start = %.2f£ \n", card.getBalance());
        card.swipeIn();
        System.out.printf("Card balance after start = %.2f£ \n", card.getBalance());
        card.swipeOut(journey2);
        System.out.printf("Card balance after finish = %.2f£ \n", card.getBalance());
        cardBalanceAfterJourney = card.getBalance();
        System.out.printf("Journey-2 fare is = %.2f£ \n\n",
                cardBalanceBeforeJourney - cardBalanceAfterJourney);

        System.out.println("Start Journey-3 from Earl’s Court to Hammersmith by Tube:");
        Journey journey3 = new Journey(earlsCourt, hammersmith, tube);
        cardBalanceBeforeJourney = card.getBalance();
        System.out.printf("Card balance before start = %.2f£ \n", card.getBalance());
        card.swipeIn();
        System.out.printf("Card balance after start = %.2f£ \n", card.getBalance());
        card.swipeOut(journey3);
        System.out.printf("Card balance after finish = %.2f£ \n", card.getBalance());
        cardBalanceAfterJourney = card.getBalance();
        System.out.printf("Journey-3 fare is = %.2f£ \n\n",
                cardBalanceBeforeJourney - cardBalanceAfterJourney);

        System.out.println("Start Journey-4 from Holborn to Hammersmith by Tube:");
        Journey journey4 = new Journey(holborn, hammersmith, tube);
        cardBalanceBeforeJourney = card.getBalance();
        System.out.printf("Card balance before start = %.2f£ \n", card.getBalance());
        card.swipeIn();
        System.out.printf("Card balance after start = %.2f£ \n", card.getBalance());
        card.swipeOut(journey4);
        System.out.printf("Card balance after finish = %.2f£ \n", card.getBalance());
        cardBalanceAfterJourney = card.getBalance();
        System.out.printf("Journey-4 fare is = %.2f£ \n\n",
                cardBalanceBeforeJourney - cardBalanceAfterJourney);

        System.out.println("Remaining balance: £" + card.getBalance());
    }
}
