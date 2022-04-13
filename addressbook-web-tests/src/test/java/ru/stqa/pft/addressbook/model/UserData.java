package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserData {

    private int id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String workphone;
    private final String email;
    private String group;

    public UserData(String firstname, String lastname, String address, String workphone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.workphone = workphone;
        this.email = email;
        this.group = group;
    }

    public UserData(int id, String firstname, String lastname, String address, String workphone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.workphone = workphone;
        this.email = email;
        this.group = null;
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

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(firstname, userData.firstname) && Objects.equals(lastname, userData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
