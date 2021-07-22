package com.example.jpa.service;

import com.example.jpa.pojo.Person;
import org.springframework.stereotype.Service;

@Service
public interface personService {

    /**
     *
     * @return
     */
    String findAll();

    String findByNameLike(String KeyName);

    String findPeopleWithLogin(String name, String password);

    String savePerson(Person p);

    String updatePerson(Person p);

    String deletePersonById(Integer id);

    String deleteAll();
}
