package com.tc.models;

import java.util.Objects;

public class Contact {

    private String name;
    private Long phone;
    private Long noOfSpams;

    public Contact(String name, Long phone) {
        this.name = name;
        this.phone = phone;
        this.noOfSpams = 0L;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getNoOfSpams() {
        return noOfSpams;
    }

    public void markSpam() {
        this.noOfSpams = this.noOfSpams + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
