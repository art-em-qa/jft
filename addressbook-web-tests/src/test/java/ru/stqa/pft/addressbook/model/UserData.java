package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String workphone;
    private final String email;

    public UserData(String firstname, String lastname, String address, String workphone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.workphone = workphone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getEmail() {
        return email;
    }
}
