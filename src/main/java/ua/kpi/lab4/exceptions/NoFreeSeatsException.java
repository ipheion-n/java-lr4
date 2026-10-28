package ua.kpi.lab4.exceptions;

/** Виникає при спробі посадити пасажира, коли всі місця зайнято. */
public class NoFreeSeatsException extends RuntimeException {
    public NoFreeSeatsException(String message) {
        super(message);
    }
}
