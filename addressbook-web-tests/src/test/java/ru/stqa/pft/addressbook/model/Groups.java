package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> deligate;

    public Groups(Groups groups){
        this.deligate =new HashSet<GroupData>(groups.deligate);
    }

    public Groups() {
        this.deligate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.deligate =new HashSet<GroupData>(groups);
    }

    @Override
    protected Set delegate() {
        return deligate;
    }

    public Groups withAdded(GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
