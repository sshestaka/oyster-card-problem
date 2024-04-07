import org.junit.Test;
import org.oyster_card_task.card.OysterCard;
import org.oyster_card_task.exceptions.EmptyZonesException;
import org.oyster_card_task.exceptions.InsufficientFundsOnTheCardException;
import org.oyster_card_task.journey.Journey;
import org.oyster_card_task.station.Station;
import org.oyster_card_task.transport.TransportType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class OysterCardTest {
    private static final double DELTA = 0.001;
    private static final double AMOUNT_30_POUNDS = 30.00;
    private static final double AMOUNT_LESS_3_POUNDS = 2.50;
    private static final double ZONE_1 = 2.50; // Anywhere in Zone 1
    private static final double ZONE_OUTSIDE_1 = 2.00; // Any one zone outside Zone 1
    private static final double TWO_ZONES_INCLUDING_1 = 3.00; // Any two zones including Zone 1
    private static final double TWO_ZONES_EXCLUDING_1 = 2.25; // Any two zones excluding Zone 1
    private static final double THREE_ZONES = 3.20; // Any three zones
    private static final double BUS_FARE = 1.80;

    @Test
    public void testLoadCard_ShouldReturnCorrectBalance() {
        OysterCard card = new OysterCard();
        card.topUp(AMOUNT_30_POUNDS);
        assertEquals(AMOUNT_30_POUNDS, card.getBalance(), DELTA);
    }

    @Test
    public void testHolbornToEarCourtByTube_ShouldReceiveZone1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station holborn = Station.HOLBORN;
        Station earlsCourt = Station.EARLS_COURT;

        Journey journey = new Journey(earlsCourt, holborn, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - ZONE_1, card.getBalance(), DELTA);
    }

    @Test
    public void testEarCourtToWimbledonByTube_ShouldReceiveTwoZonesExcluding1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station earlsCourt = Station.EARLS_COURT;
        Station wimbledon = Station.WIMBLEDON;

        Journey journey = new Journey(earlsCourt, wimbledon, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - TWO_ZONES_EXCLUDING_1, card.getBalance(), DELTA);
    }

    @Test
    public void testHolbornToHammersmithByTube_ShouldReceiveTwoZonesIncluding1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station holborn = Station.HOLBORN;
        Station hammersmith = Station.HAMMERSMITH;

        Journey journey = new Journey(holborn, hammersmith, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - TWO_ZONES_INCLUDING_1, card.getBalance(), DELTA);
    }

    @Test
    public void testHammersmithToWimbledonByTube_ShouldReceiveTwoZonesExcluding1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station hammersmith = Station.HAMMERSMITH;
        Station wimbledon = Station.WIMBLEDON;

        Journey journey = new Journey(hammersmith, wimbledon, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - TWO_ZONES_EXCLUDING_1, card.getBalance(), DELTA);
    }

    @Test
    public void testHolbornToWimbledonByTube_ShouldReceiveThreeZonesFare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station holborn = Station.HOLBORN;
        Station wimbledon = Station.WIMBLEDON;

        Journey journey = new Journey(holborn, wimbledon, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - THREE_ZONES, card.getBalance(), DELTA);
    }

    @Test
    public void testHolbornToHolbornByTube_ShouldReceiveZoneZone1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station holbornStart = Station.HOLBORN;
        Station holbornFinish = Station.HOLBORN;

        Journey journey = new Journey(holbornStart, holbornFinish, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - ZONE_1, card.getBalance(), DELTA);
    }

    @Test
    public void testEarCourtToEarCourtByTube_ShouldReceiveZoneOutside1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station earlsCourtStart = Station.EARLS_COURT;
        Station earlsCourtFinish = Station.EARLS_COURT;

        Journey journey = new Journey(earlsCourtStart, earlsCourtFinish, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - ZONE_OUTSIDE_1, card.getBalance(), DELTA);
    }

    @Test
    public void testWimbledonToWimbledonByTube_ShouldReceiveZoneOutside1Fare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station wimbledonStart = Station.WIMBLEDON;
        Station wimbledonFinish = Station.WIMBLEDON;

        Journey journey = new Journey(wimbledonStart, wimbledonFinish, TransportType.TUBE);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - ZONE_OUTSIDE_1, card.getBalance(), DELTA);
    }

    @Test
    public void testHolbornToChelseaByBus_ShouldReceiveBusFare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station holborn = Station.HOLBORN;
        Station chelsea = Station.CHELSEA;

        Journey journey = new Journey(holborn, chelsea, TransportType.BUS);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - BUS_FARE, card.getBalance(), DELTA);
    }

    @Test
    public void testEarCourtToWimbledonByBus_ShouldReceiveBusFare() {
        OysterCard card = new OysterCard(AMOUNT_30_POUNDS);
        Station earlsCourt = Station.EARLS_COURT;
        Station wimbledon = Station.WIMBLEDON;

        Journey journey = new Journey(earlsCourt, wimbledon, TransportType.BUS);

        double initialBalance = card.getBalance();
        card.swipeIn();
        card.swipeOut(journey);

        assertEquals(initialBalance - BUS_FARE, card.getBalance(), DELTA);
    }

    @Test
    public void testSwipeInWithInsufficientFunds_ShouldThrowInsufficientFundsOnTheCardException() {
        OysterCard card = new OysterCard(AMOUNT_LESS_3_POUNDS);
        Station holborn = Station.HOLBORN;
        Station earlsCourt = Station.EARLS_COURT;
        Journey journey = new Journey(holborn, earlsCourt, TransportType.TUBE);
        assertThrows(InsufficientFundsOnTheCardException.class, card::swipeIn);
    }

    @Test
    public void testSwipeOutEmptyZonesByTube_ShouldThrowEmptyZonesException() {
        OysterCard card = new OysterCard(30);
        Station holborn = Station.HOLBORN;
        Station chelsea = Station.CHELSEA;
        // Journey with empty end station zones
        Journey journey = new Journey(holborn, chelsea, TransportType.TUBE);
        assertThrows(EmptyZonesException.class, () -> card.swipeOut(journey));
    }

    @Test
    public void testDemonstrateRequiredOperations_OK() {
        OysterCard card = new OysterCard();
        card.topUp(AMOUNT_30_POUNDS);

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
        assertEquals(cardBalanceBeforeJourney - ZONE_1, cardBalanceAfterJourney, DELTA);


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
        assertEquals(cardBalanceBeforeJourney - BUS_FARE, cardBalanceAfterJourney, DELTA);

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
        assertEquals(cardBalanceBeforeJourney - ZONE_OUTSIDE_1, cardBalanceAfterJourney, DELTA);

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
        assertEquals(cardBalanceBeforeJourney - TWO_ZONES_INCLUDING_1, cardBalanceAfterJourney, DELTA);
    }
}
