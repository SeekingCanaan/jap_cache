package com.example.jpa.repository;

import com.example.jpa.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    @Test
    void getAll() {
        System.out.println(personRepository.findAll());
    }

    @Test
    void findByKeyName() {
        List<Person> personList = personRepository.findByNameLike("12");
        System.out.println(personList);
    }

    @Test
    void Login() {
        System.out.println(personRepository.findPeopleWithLogin("admin", "12345"));
    }

    @Test
    void findById() {
        System.out.println(personRepository.findById(3));
    }
}