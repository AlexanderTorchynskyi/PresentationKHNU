package io.devsmile.khnu.web.ui;

import io.devsmile.khnu.model.Person;
import io.devsmile.khnu.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final static Logger LOG = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public String create(@ModelAttribute("newPerson") Person person) {
        LOG.info("student with email {} will go to devsmile {} ", person.getEmail(), person.isWillIGoToDevSmile());
        personService.createOrUpdate(person);
        return "person/thankYou";
    }

    @GetMapping
    public String getNewPersonForm(Model model) {
        Person person = new Person();
        model.addAttribute("newPerson", person);
        return "person/newPersonForm";
    }
}
