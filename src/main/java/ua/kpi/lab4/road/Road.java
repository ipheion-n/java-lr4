package ua.kpi.lab4.road;

import ua.kpi.lab4.humans.Human;
import ua.kpi.lab4.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Ділянка дороги. Зберігає транспортні засоби з будь-якими типами пасажирів,
 * тому використовується wildcard «Vehicle&lt;? extends Human&gt;».
 */
public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    /** Кількість людей, які перевозяться транспортом на цій ділянці дороги. */
    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }

    /** Додати транспортний засіб з будь-яким типом пасажирів. */
    public void addCarToRoad(Vehicle<? extends Human> car) {
        if (car == null) {
            throw new IllegalArgumentException("Транспортний засіб не може бути null");
        }
        carsInRoad.add(car);
    }

    /** Додати кілька транспортних засобів одразу (наприклад, List&lt;FireTruck&gt;). */
    public void addAllCarsToRoad(Collection<? extends Vehicle<? extends Human>> cars) {
        for (Vehicle<? extends Human> car : cars) {
            addCarToRoad(car);
        }
    }
}
