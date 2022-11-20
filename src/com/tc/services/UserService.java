package com.tc.services;

import com.tc.exceptions.AlreadyExistsException;
import com.tc.models.Contact;
import com.tc.models.User;
import com.tc.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private Set<User> users;
    private Set<Contact> contacts;

    public UserService() {
        this.users = new HashSet<>();
        this.contacts = new HashSet<>();
    }

    public void createUser(User user) {
        if (users.contains(user))
            throw new AlreadyExistsException("User already exists");
        users.add(user);
    }

    public User getByPhoneNumber(Long phoneNumber) {
        return users.stream().filter(user -> Objects.deepEquals(phoneNumber, user.getPhoneNumber())).findFirst().orElse(null);
    }

    public List<User> getUserByName(String name) {
        return users.stream().filter(user -> Objects.deepEquals(name, user.getName())).collect(Collectors.toList());
    }


    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    // get call id info
    private Contact getContactInfo(Long phone) {
        return contacts.stream().filter(contact -> Objects.deepEquals(phone, contact.getPhone()))
                .findFirst().orElse(null);
    }

    private List<Contact> getContactInfoByName(String name) {
        return contacts.stream().filter(contact -> Objects.deepEquals(name, contact.getName()))
                .collect(Collectors.toList());
    }

    public Contact getCallerInfo(Long phone) {
        Contact contact = getContactInfo(phone);
        if (contact == null) {
            User user = getByPhoneNumber(phone);
            if (user == null)
                contact = new Contact(null, phone);
            else
                contact = new Contact(user.getName(), user.getPhoneNumber());
        }
        return contact;
    }

    public List<Contact> getCallerInfoByName(String name){
        List<Contact> contactList = getContactInfoByName(name);
        if (CollectionUtil.isEmpty(contactList)) {
            List<User> users = getUserByName(name);
            if(!CollectionUtil.isEmpty(users))
                contactList = users.stream().map(user -> {
                    Contact contact = new Contact(user.getName(), user.getPhoneNumber());
                    return contact;
                }).collect(Collectors.toList());
        }
        return contactList;
    }

    public void importContact(User user, Set<Contact> contact){
        user.getUserContacts().importContacts(contact);
        contacts.addAll(contact);
    }

}
