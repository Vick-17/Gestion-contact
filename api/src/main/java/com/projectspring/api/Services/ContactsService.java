package com.projectspring.api.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.ContactsDto;
import com.projectspring.api.Dto.UserDto;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.ContactsMapper;
import com.projectspring.api.Models.Contacts;
import com.projectspring.api.Models.UserEntities;
import com.projectspring.api.Repositories.ContactsRepositories;
import com.projectspring.api.Repositories.UserRepositories;

@Service
public class ContactsService extends GenericServiceImpl<Contacts, Integer, ContactsDto, ContactsRepositories, ContactsMapper> implements GenericService<ContactsDto, Integer> {

    @Autowired
    private UserRepositories userRepositories;

    public ContactsService(ContactsRepositories repository, ContactsMapper mapper) {
        super(repository, mapper);
    }

    public ContactsDto createContact(ContactsDto contact, int idUser) {
        userRepositories.findById(idUser).ifPresent(user -> {
            contact.getUsers().add(user);
        });
        return saveOrUpdate(contact);
    }

    public void deleteContactByUserId(Integer contactId, Integer userId) {
        Contacts contact = repository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact non trouvé"));

        UserEntities user = userRepositories.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (user.getRoles().isEmpty()) {
            throw new RuntimeException("Utilisateur non connecté");
        }

        boolean isUserOwner = contact.getUsers().stream()
                .anyMatch(u -> Integer.valueOf(u.getId()).equals(userId));

        if (!isUserOwner) {
            throw new RuntimeException("Interdiction de supprimer le contact");
        }

        repository.deleteById(contactId);
    }

    public ContactsDto updateContactByUserId(Integer contactId, Integer userId, ContactsDto contactUpdate) {
        Contacts contact = repository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact introuvable"));


        boolean isUserLinked = contact.getUsers().stream()
                .anyMatch(user -> user.getId() == userId);

        if (!isUserLinked) {
            throw new RuntimeException("pas la mermision de modifier ce contact");
        }

   
        contact.setName(contactUpdate.getName());
        contact.setPhone(contactUpdate.getPhone());
        contact.setAdresse(contactUpdate.getAddresse());

        Contacts updatedContact = repository.save(contact);
        return toDto(updatedContact);
    }

}
