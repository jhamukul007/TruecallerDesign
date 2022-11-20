package com.tc.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserContact {
    private Set<Contact> contacts;

    public UserContact() {
        this.contacts = new HashSet<>();
    }

    public void addContact(Contact contact) {
        if(contacts.contains(contact))
            return;
        contacts.add(contact);
    }

    public void importContacts(Set<Contact> importContacts) {
        contacts.addAll(importContacts);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact getContactByPhone(Long phone) {
        return contacts.stream().filter(contact -> Objects.deepEquals(phone, contact.getPhone()))
                .findFirst().orElse(null);
    }

    public Contact getContactByName(String name) {
        return contacts.stream().filter(contact -> Objects.deepEquals(name, contact.getName()))
                .findFirst().orElse(null);
    }

}
