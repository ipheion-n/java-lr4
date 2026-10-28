package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.humans.Human;

/** Таксі — може перевозити будь-яких пасажирів. */
public class Taxi<T extends Human> extends Car<T> {
    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
