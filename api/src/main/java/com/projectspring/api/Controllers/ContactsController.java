package com.projectspring.api.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.api.Dto.ContactsDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.ContactsService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contacts")
public class ContactsController extends GenericController<ContactsDto, Integer, ContactsService> {
    public ContactsController(ContactsService service) {
        super(service);
    }

    @PostMapping("/create")
    public ContactsDto createContact(@RequestBody ContactsDto contact, int idUser) {
        return service.createContact(contact, idUser);
    }

    @DeleteMapping("/supr")
    public void deleteContactByUserId(@RequestParam Integer contactId, Integer userId) {
        service.deleteContactByUserId(contactId, userId);
    }

    @PutMapping("/modif/{contactId}")
    public ContactsDto updateContact(@RequestBody ContactsDto contact, Integer contactId, Integer userId) {
        return service.updateContactByUserId(contactId, userId, contact);
    }
    
}
