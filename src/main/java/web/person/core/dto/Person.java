package web.person.core.dto;

public class Person {
    private String lastNAme;
    private String firstName;
    private int age;

    public Person(String lastNAme, String firstName, int age) {
        this.lastNAme = lastNAme;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastNAme() {
        return lastNAme;
    }


    public String getFirstName() {
        return firstName;
    }


    public int getAge() {
        return age;
    }

}
