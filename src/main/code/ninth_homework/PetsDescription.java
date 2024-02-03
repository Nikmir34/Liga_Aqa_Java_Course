package code.ninth_homework;

public class PetsDescription {
    public static void main(String[] args) {
        Pet animal = new Pet(); // Вызов первого конструктора
        animal.displayInfo();

        Pet description = new Pet("Собака", "Домашкинс", 5, "Цвергшнауцер"); // Вызов второго конструктора
        description.displayInfo();
    }
}
