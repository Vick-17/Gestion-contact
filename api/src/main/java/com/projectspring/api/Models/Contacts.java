package com.projectspring.api.Models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import jakarta.persistence.JoinColumn;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String adresse;

    @ManyToMany
    @JoinTable(name = "users_contacts", joinColumns = @JoinColumn(name = "id_contacts"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Collection<UserEntities> users = new ArrayList<>();
}
