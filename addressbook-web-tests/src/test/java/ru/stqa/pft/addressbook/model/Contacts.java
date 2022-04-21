package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> deligate;

    public Contacts(Contacts contacts){
        this.deligate =new HashSet<ContactData>(contacts.deligate);
    }

    public Contacts() {
        this.deligate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.deligate =new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set delegate() {
        return deligate;
    }

    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
