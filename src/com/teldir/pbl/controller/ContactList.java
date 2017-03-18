package com.teldir.pbl.controller;

import com.teldir.pbl.model.Contacts;

import java.util.List;

/**
 * Created by arvindhn602 on 3/16/2017.
 */
public class ContactList {
    private List<Contacts> contacts;


        public List<Contacts> getPersons() {
            return contacts;
        }

        public void setPersons(List<Contacts> persons) {
            this.contacts = persons;
        }
}
