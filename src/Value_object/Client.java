package Value_object;

public class Client {
    String name;
    String surname;
    int age;
    Voiture location;

    Client(String name, String surname, int age, Voiture location){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.location = location;
    }
}
