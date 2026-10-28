package ua.kpi.lab4;

import ua.kpi.lab4.exceptions.NoFreeSeatsException;
import ua.kpi.lab4.exceptions.PassengerNotFoundException;
import ua.kpi.lab4.humans.Firefighter;
import ua.kpi.lab4.humans.Human;
import ua.kpi.lab4.humans.Policeman;
import ua.kpi.lab4.road.Road;
import ua.kpi.lab4.vehicles.Bus;
import ua.kpi.lab4.vehicles.FireTruck;
import ua.kpi.lab4.vehicles.PoliceCar;
import ua.kpi.lab4.vehicles.Taxi;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Human ivan = new Human("Іван");
        Firefighter petro = new Firefighter("Петро");
        Firefighter oleh = new Firefighter("Олег");
        Policeman anna = new Policeman("Анна");

        Bus<Human> bus = new Bus<>(30);
        bus.boardPassenger(ivan);
        bus.boardPassenger(petro);
        bus.boardPassenger(anna);

        Taxi<Human> taxi = new Taxi<>(4);
        taxi.boardPassenger(new Human("Марія"));

        FireTruck fireTruck = new FireTruck(2);
        fireTruck.boardAll(List.of(new Firefighter("Андрій"), oleh));
        // fireTruck.boardPassenger(anna);  // помилка компіляції: Policeman не є Firefighter

        PoliceCar policeCar = new PoliceCar(4);
        policeCar.boardPassenger(new Policeman("Дмитро"));
        // policeCar.boardPassenger(ivan);  // помилка компіляції: Human не є Policeman

        System.out.println("Автобус: " + bus + " " + bus.getPassengers());
        System.out.println("Таксі: " + taxi + " " + taxi.getPassengers());
        System.out.println("Пожежна: " + fireTruck + " " + fireTruck.getPassengers());
        System.out.println("Поліцейська: " + policeCar + " " + policeCar.getPassengers());

        try {
            fireTruck.boardPassenger(new Firefighter("Зайвий"));
        } catch (NoFreeSeatsException e) {
            System.out.println("Виняток: " + e.getMessage());
        }
        try {
            taxi.dropPassenger(ivan);
        } catch (PassengerNotFoundException e) {
            System.out.println("Виняток: " + e.getMessage());
        }

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);
        System.out.println("Людей на ділянці дороги: " + road.getCountOfHumans());
    }
}
