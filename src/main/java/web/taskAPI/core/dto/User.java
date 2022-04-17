package web.taskAPI.core.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String thirdName;
    private LocalDate dateOfBirthday;
    private LocalDateTime dateOfRegistration;
    private String role;

    public User(String login, String password, String lastName,String firstName, String thirdName, LocalDate dateOfBirthday) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.thirdName = thirdName;
        this.dateOfBirthday = dateOfBirthday;
        this.dateOfRegistration = LocalDateTime.now();
        this.role = String.valueOf(Role.USER);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

