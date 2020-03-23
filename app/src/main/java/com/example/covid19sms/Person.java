package com.example.covid19sms;

public class Person {
    private String firstname;
    private String lastname;
    private String Address;

    public Person(String firstname, String lastname, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        Address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return Address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
