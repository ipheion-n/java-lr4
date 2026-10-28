package ua.kpi.lab4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.kpi.lab4.exceptions.NoFreeSeatsException;
import ua.kpi.lab4.exceptions.PassengerNotFoundException;
import ua.kpi.lab4.humans.Firefighter;
import ua.kpi.lab4.humans.Human;
import ua.kpi.lab4.humans.Policeman;
import ua.kpi.lab4.vehicles.Bus;
import ua.kpi.lab4.vehicles.FireTruck;
import ua.kpi.lab4.vehicles.PoliceCar;
import ua.kpi.lab4.vehicles.Taxi;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    @DisplayName("Автобус перевозить пасажирів усіх типів")
    void busAcceptsAllTypesOfPassengers() {
        Bus<Human> bus = new Bus<>(10);
        bus.boardPassenger(new Human("Іван"));
        bus.boardPassenger(new Firefighter("Петро"));
        bus.boardPassenger(new Policeman("Анна"));

        assertEquals(10, bus.getMaxSeats());
        assertEquals(3, bus.getOccupiedSeats());
    }

    @Test
    @DisplayName("Таксі перевозить пасажирів усіх типів")
    void taxiAcceptsAllTypesOfPassengers() {
        Taxi<Human> taxi = new Taxi<>(4);
        taxi.boardPassenger(new Human("Марія"));
        taxi.boardPassenger(new Firefighter("Олег"));
        taxi.boardPassenger(new Policeman("Дмитро"));

        assertEquals(4, taxi.getMaxSeats());
        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    @DisplayName("Пожежна машина приймає пожежників")
    void fireTruckAcceptsFirefighters() {
        FireTruck truck = new FireTruck(6);
        truck.boardPassenger(new Firefighter("Андрій"));
        truck.boardPassenger(new Firefighter("Олег"));

        assertEquals(2, truck.getOccupiedSeats());
        // truck.boardPassenger(new Policeman("Анна")); — не компілюється
        // truck.boardPassenger(new Human("Іван"));     — не компілюється
    }

    @Test
    @DisplayName("Поліцейська машина приймає поліцейських")
    void policeCarAcceptsPolicemen() {
        PoliceCar car = new PoliceCar(4);
        car.boardPassenger(new Policeman("Дмитро"));

        assertEquals(1, car.getOccupiedSeats());
        // car.boardPassenger(new Firefighter("Олег")); — не компілюється
    }

    @Test
    @DisplayName("Посадка у заповнений транспорт кидає NoFreeSeatsException")
    void boardingFullVehicleThrows() {
        Taxi<Human> taxi = new Taxi<>(2);
        taxi.boardPassenger(new Human("А"));
        taxi.boardPassenger(new Human("Б"));

        assertThrows(NoFreeSeatsException.class, () -> taxi.boardPassenger(new Human("В")));
        assertEquals(2, taxi.getOccupiedSeats());
    }

    @Test
    @DisplayName("Висадка пасажира звільняє місце")
    void droppingPassengerFreesSeat() {
        PoliceCar car = new PoliceCar(1);
        Policeman anna = new Policeman("Анна");
        car.boardPassenger(anna);
        car.dropPassenger(anna);

        assertEquals(0, car.getOccupiedSeats());
        car.boardPassenger(new Policeman("Дмитро"));
        assertEquals(1, car.getOccupiedSeats());
    }

    @Test
    @DisplayName("Висадка відсутнього пасажира кидає PassengerNotFoundException")
    void droppingAbsentPassengerThrows() {
        FireTruck truck = new FireTruck(4);
        truck.boardPassenger(new Firefighter("Андрій"));

        assertThrows(PassengerNotFoundException.class,
                () -> truck.dropPassenger(new Firefighter("Олег")));
    }

    @Test
    @DisplayName("boardAll: група пожежників (List<Firefighter>) сідає в автобус Bus<Human>")
    void boardAllAcceptsSubtypeCollection() {
        Bus<Human> bus = new Bus<>(5);
        List<Firefighter> crew = List.of(new Firefighter("А"), new Firefighter("Б"));
        bus.boardAll(crew);

        assertEquals(2, bus.getOccupiedSeats());
    }

    @Test
    @DisplayName("boardAll: група більша за кількість вільних місць — ніхто не сідає")
    void boardAllTooBigGroupThrows() {
        FireTruck truck = new FireTruck(2);
        List<Firefighter> crew = List.of(
                new Firefighter("А"), new Firefighter("Б"), new Firefighter("В"));

        assertThrows(NoFreeSeatsException.class, () -> truck.boardAll(crew));
        assertEquals(0, truck.getOccupiedSeats());
    }

    @Test
    @DisplayName("dropAllTo: пожежники висаджуються у List<Human>")
    void dropAllToSupertypeCollection() {
        FireTruck truck = new FireTruck(3);
        truck.boardAll(List.of(new Firefighter("А"), new Firefighter("Б")));
        List<Human> station = new ArrayList<>();
        truck.dropAllTo(station);

        assertEquals(2, station.size());
        assertEquals(0, truck.getOccupiedSeats());
    }

    @Test
    @DisplayName("Список пасажирів не можна змінити ззовні")
    void passengersListIsUnmodifiable() {
        Taxi<Human> taxi = new Taxi<>(4);
        taxi.boardPassenger(new Human("Іван"));

        assertThrows(UnsupportedOperationException.class,
                () -> taxi.getPassengers().add(new Human("Хтось")));
    }

    @Test
    @DisplayName("Некоректна кількість місць кидає IllegalArgumentException")
    void invalidSeatCountThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Bus<Human>(0));
    }
}
