package lv.arcana.hobiji.controllers;

import lv.arcana.hobiji.models.Hobby;
import lv.arcana.hobiji.models.Person;
import lv.arcana.hobiji.models.Status;
import lv.arcana.hobiji.services.Services;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class PersonController {

    @Autowired
    private Services services;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public Status addPerson(@RequestBody String person) {
        Status s = new Status();
        s.setResult("OK");

        try {
            Person p = mapper.readValue(person,Person.class);
            services.addPerson(p);
        } catch (Exception e) {
            e.printStackTrace();
            s.setResult("FAIL");
            s.setMessage(e.getMessage());
        }

        return s;
    }

    @RequestMapping(value = "persons/{id}", method = RequestMethod.PUT)
    public Status editPerson(@RequestBody String person, @PathVariable Long id) {
        Status s = new Status();
        s.setResult("OK");

        try {
            Person p = mapper.readValue(person,Person.class);
            services.updatePerson(p);
        } catch (Exception e) {
            e.printStackTrace();
            s.setResult("FAIL");
            s.setMessage(e.getMessage());
        }

        return s;
    }

    @RequestMapping("persons/{id}")
    public Person viewPerson(@PathVariable Long id) {
        Person p = null;

        try {
            p = services.findPerson(id);
            for(Hobby h: p.getHobbies()) {
                h.setPersons(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    @RequestMapping("/persons")
    public List<Person> list() {
        List<Person> persons = services.fetchAllPersons();
        for(Person p: persons) {
            for(Hobby h: p.getHobbies()) {
                h.setPersons(null);
            }
        }

        return persons;
    }

    @RequestMapping(value = "persons/{id}", method = RequestMethod.DELETE)
    public Status deletePerson(@PathVariable Long id) {
        Status s = new Status();
        s.setResult("OK");

        try {
            services.deletePerson(id);
        } catch (Exception e) {
            e.printStackTrace();
            s.setResult("FAIL");
            s.setMessage(e.getMessage());
        }

        return s;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

}
