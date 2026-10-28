package ua.kpi.lab4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.kpi.lab4.humans.Firefighter;
import ua.kpi.lab4.humans.Human;
import ua.kpi.lab4.humans.Policeman;
import ua.kpi.lab4.road.Road;
import ua.kpi.lab4.vehicles.Bus;
import ua.kpi.lab4.vehicles.FireTruck;
import ua.kpi.lab4.vehicles.PoliceCar;
import ua.kpi.lab4.vehicles.Taxi;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    @Test
    @DisplayName("Порожня дорога — 0 людей")
    void emptyRoadHasNoHumans() {
        assertEquals(0, new Road().getCountOfHumans());
    }

    @Test
    @DisplayName("Підрахунок людей у транспорті з різними типами пасажирів")
    void countsHumansInAllVehicles() {
        Bus<Human> bus = new Bus<>(30);
        bus.boardPassenger(new Human("Іван"));
        bus.boardPassenger(new Firefighter("Петро"));
        bus.boardPassenger(new Policeman("Анна"));

        Taxi<Human> taxi = new Taxi<>(4);
        taxi.boardPassenger(new Human("Марія"));

        FireTruck truck = new FireTruck(6);
        truck.boardPassenger(new Firefighter("Андрій"));
        truck.boardPassenger(new Firefighter("Олег"));

        PoliceCar police = new PoliceCar(4);
        police.boardPassenger(new Policeman("Дмитро"));

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(truck);
        road.addCarToRoad(police);

        assertEquals(4, road.carsInRoad.size());
        assertEquals(7, road.getCountOfHumans());
    }

    @Test
    @DisplayName("Кількість людей оновлюється після висадки")
    void countReflectsDroppedPassengers() {
        PoliceCar police = new PoliceCar(4);
        Policeman anna = new Policeman("Анна");
        police.boardPassenger(anna);
        police.boardPassenger(new Policeman("Дмитро"));

        Road road = new Road();
        road.addCarToRoad(police);
        police.dropPassenger(anna);

        assertEquals(1, road.getCountOfHumans());
    }

    @Test
    @DisplayName("addAllCarsToRoad приймає List<FireTruck>")
    void addAllCarsAcceptsSpecificVehicleList() {
        FireTruck t1 = new FireTruck(4);
        t1.boardPassenger(new Firefighter("А"));
        FireTruck t2 = new FireTruck(4);
        t2.boardAll(List.of(new Firefighter("Б"), new Firefighter("В")));

        Road road = new Road();
        road.addAllCarsToRoad(List.of(t1, t2));

        assertEquals(3, road.getCountOfHumans());
    }

    @Test
    @DisplayName("Додавання null кидає IllegalArgumentException")
    void addingNullCarThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Road().addCarToRoad(null));
    }
}
