package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.humans.Firefighter;

/** Пожежна машина — перевозить тільки пожежників (тип зафіксовано: Firefighter). */
public class FireTruck extends Car<Firefighter> {
    public FireTruck(int maxSeats) {
        super(maxSeats);
    }
}
