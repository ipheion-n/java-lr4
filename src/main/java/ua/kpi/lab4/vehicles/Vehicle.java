package ua.kpi.lab4.vehicles;

import ua.kpi.lab4.exceptions.NoFreeSeatsException;
import ua.kpi.lab4.exceptions.PassengerNotFoundException;
import ua.kpi.lab4.humans.Human;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Транспортний засіб — базовий узагальнений клас.
 *
 * @param <T> тип пасажирів, яких може перевозити транспортний засіб
 *            (обмежений зверху класом Human)
 */
public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною");
        }
        this.maxSeats = maxSeats;
    }

    /** Максимальна кількість місць. */
    public int getMaxSeats() {
        return maxSeats;
    }

    /** Кількість зайнятих місць. */
    public int getOccupiedSeats() {
        return passengers.size();
    }

    /** Посадка пасажира. Якщо всі місця зайнято — NoFreeSeatsException. */
    public void boardPassenger(T passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Пасажир не може бути null");
        }
        if (passengers.size() >= maxSeats) {
            throw new NoFreeSeatsException(
                    getClass().getSimpleName() + ": усі місця зайнято (" + maxSeats + " з " + maxSeats + ")");
        }
        passengers.add(passenger);
    }

    /**
     * Посадка групи пасажирів.
     * Wildcard «? extends T» дозволяє передати колекцію підтипів T
     * (наприклад, List&lt;Firefighter&gt; в автобус Bus&lt;Human&gt;).
     */
    public void boardAll(Collection<? extends T> group) {
        if (group.size() > maxSeats - passengers.size()) {
            throw new NoFreeSeatsException(
                    getClass().getSimpleName() + ": недостатньо вільних місць для групи з "
                            + group.size() + " осіб");
        }
        for (T passenger : group) {
            boardPassenger(passenger);
        }
    }

    /** Висадка пасажира. Якщо пасажир не сидить у транспорті — PassengerNotFoundException. */
    public void dropPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException(
                    passenger + " не сидить у " + getClass().getSimpleName());
        }
    }

    /**
     * Висадка всіх пасажирів у вказану колекцію.
     * Wildcard «? super T» дозволяє отримати пасажирів у колекцію надтипу
     * (наприклад, пожежників з FireTruck у List&lt;Human&gt;).
     */
    public void dropAllTo(Collection<? super T> destination) {
        destination.addAll(passengers);
        passengers.clear();
    }

    /** Незмінний список пасажирів. */
    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + getOccupiedSeats() + "/" + maxSeats + "]";
    }
}
