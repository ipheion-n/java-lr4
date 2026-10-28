package ua.kpi.lab4.humans;

/**
 * Людина — базовий клас ієрархії пасажирів.
 * Екземпляр цього класу є «звичайним пасажиром».
 */
public class Human {
    private final String name;

    public Human(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }
}
