package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.humans.Policeman;

/** Поліцейська машина — перевозить тільки поліцейських (тип зафіксовано: Policeman). */
public class PoliceCar extends Car<Policeman> {
    public PoliceCar(int maxSeats) {
        super(maxSeats);
    }
}
