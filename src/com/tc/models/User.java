package com.tc.models;

import java.util.Objects;

public class User {

    private Integer id;
    private String name;
    private Long phoneNumber;
    private PhoneInfo phoneInfo;
    private UserContact userContacts;
    private Long spamCount;

    public User(Integer id, String name, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userContacts = new UserContact();
        this.phoneInfo = new PhoneInfo();
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public UserContact getUserContacts() {
        return userContacts;
    }

    public void addUserContact(UserContact userContacts) {
        this.userContacts = userContacts;
    }

    public Long getSpamCount() {
        return spamCount;
    }

    public void setSpamCount(Long spamCount) {
        this.spamCount = spamCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

}
