package ua.kpi.lab4.exceptions;

/** Виникає при спробі висадити пасажира, який не сидить у транспортному засобі. */
public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
