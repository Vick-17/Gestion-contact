package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.ContactsDto;
import com.projectspring.api.Generic.GenericMapper;
import com.projectspring.api.Models.Contacts;

@Mapper
public interface ContactsMapper extends GenericMapper<Contacts, ContactsDto> {

    
}
