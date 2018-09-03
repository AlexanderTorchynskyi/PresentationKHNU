package io.devsmile.khnu.service;

import io.devsmile.khnu.model.Person;
import io.devsmile.khnu.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> get(ObjectId id) {
        return personRepository.findById(id);
    }

    public Page<Person> get(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public void createOrUpdate(Person person) {
        personRepository.save(person);
    }

    public void delete(ObjectId id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
    }
}