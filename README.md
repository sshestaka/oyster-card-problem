# The Oyster Card Problem

## The task is to model the following travel card system, which is a limited version of London's Oyster card system.
<br>

**Operational Requirements**<br>
When the user passes through the inward barrier at the station, 
their oyster card is charged the maximum fare.<br>

When they pass out of the barrier at the exit station, 
the fare is calculated and the maximum fare transaction removed and
replaced with the real transaction (in this way, if the user doesn’t
swipe out, they are charged the maximum fare).<br>

**Note:** Please program entering/exiting the stations as
separate steps, as they are in real life.<br>

**All bus journeys are charged at the same price.**

**The system should favor the customer where more than one fare is possible for a given journey.**
E.g. Holburn to Earl’s Court is charged at £2.50, and Earl's Court to Wimbledon is £2.25.<br>

**Note:** Going from station Holborn (zone 1) to Wimbledon (zone3) 
means going through three zones (1 -> 2 -> 3).<br>

## Technologies and tools:
* Java Development Kit (JDK) Version: 21
* Apache Maven Version: 4.0.0
* JUnit 4.13.2
* IDE IntelliJ IDEA 2023.3.4

## Work with Application

**1. For use the application you have to clone it into your IDE.<br>**
**2. Build using Maven.<br>**
**3. Run the Main method - it will show all the basic operations and logic. 
Feel free to change any conditions in the Main method to test the input.<br>**
**4. Run OysterCardTest to test all possible cases of this task.<br>**

