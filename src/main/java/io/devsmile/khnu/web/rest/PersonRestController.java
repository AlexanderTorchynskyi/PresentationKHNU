package io.devsmile.khnu.web.rest;

import io.devsmile.khnu.model.Person;
import io.devsmile.khnu.service.PersonService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonRestController {

    private final static Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable ObjectId id) {
        if (personService.get(id).isPresent()) {
            return ResponseEntity.ok(personService.get(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Person>> get(Pageable pageable) {
        return ResponseEntity.ok(personService.get(pageable));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Person person) {
        LOG.info("student with email {} will go to devsmile {} ", person.getEmail(), person.isWillIGoToDevSmile());

        personService.createOrUpdate(person);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Person person) {
        personService.createOrUpdate(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
