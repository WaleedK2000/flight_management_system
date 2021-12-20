import java.nio.ByteBuffer;

public class Passenger {
    private String name;
    private int age;
    private char gender;

    public Passenger() {
        name = "Jhon Doe";
        age = 29;
        gender = 'M';
    }

    public Passenger(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Passenger(String name, String age, String gender) {
        this.name = name;
        this.age = Integer.parseInt(age);
        if (gender == "MALE") {
            this.gender = 'M';
        } else {
            this.gender = 'F';
        }
    }

    public Passenger(String name, String age, char gender) {
        this.name = name;
        this.age = Integer.parseInt(age);
        this.gender = gender;
    }

    public void writeData(ByteBuffer write) {
        write.put(name.getBytes());
        write.put(";".getBytes());
        write.put(String.valueOf(age).getBytes());
        write.put(";".getBytes());

        String gender_t;
        if (gender == 'M') {
            gender_t = "MALE";
        } else {
            gender_t = "FEMALE";
        }

        write.put(gender_t.getBytes());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public void printPassengerData() {
        System.out.println(name + " " + age);
    }

}
