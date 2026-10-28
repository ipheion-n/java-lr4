package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.humans.Human;

/** Автобус — може перевозити будь-яких пасажирів. */
public class Bus<T extends Human> extends Vehicle<T> {
    public Bus(int maxSeats) {
        super(maxSeats);
    }
}
