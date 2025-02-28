package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Models.Contacts;


public interface ContactsRepositories extends JpaRepository<Contacts, Integer> {
    
}
