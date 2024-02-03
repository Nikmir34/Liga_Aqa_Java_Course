package code.ninth_homework;

public class Pet {
    String kind;
    String nickname;
    int age;
    String breed;

    // Конструктор 1
    Pet()
    {
        this("Кошка", "Хоумврокинс", 3, "Русская голубая");
    }
    Pet(String kind, String nickname, int age, String breed)
    // Конструктор 2
    {
        this.kind = kind;
        this.nickname = nickname;
        this.age = age;
        this.breed = breed;
    }
        void displayInfo(){
        System.out.printf("Вид - %s\nКличка - %s\nВозраст - %d\nПорода - %s\n\n", kind, nickname, age, breed);
    }

}
