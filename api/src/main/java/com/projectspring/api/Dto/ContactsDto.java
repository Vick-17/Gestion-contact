package com.projectspring.api.Dto;

import java.util.ArrayList;
import java.util.Collection;

import com.projectspring.api.Models.UserEntities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactsDto {
    
    private String name;

    private String phone;

    private String addresse;

    private Collection<UserEntities> users = new ArrayList<>();
}
