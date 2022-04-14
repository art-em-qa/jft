package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<UserData> {

    private Set<UserData> deligate;

    public Contacts(Contacts contacts){
        this.deligate =new HashSet<UserData>(contacts.deligate);
    }

    public Contacts() {
        this.deligate = new HashSet<UserData>();
    }

    @Override
    protected Set delegate() {
        return deligate;
    }

    public Contacts withAdded(UserData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(UserData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
