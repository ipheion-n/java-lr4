package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.humans.Human;

/** Автомобіль — проміжний абстрактний клас ієрархії. */
public abstract class Car<T extends Human> extends Vehicle<T> {
    protected Car(int maxSeats) {
        super(maxSeats);
    }
}
