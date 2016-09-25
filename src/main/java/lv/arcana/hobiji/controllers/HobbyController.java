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
public class HobbyController {

    @Autowired
    private Services services;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/hobbies", method = RequestMethod.POST)
    public Status addHobby(@RequestBody String hobby) {
        Status s = new Status();
        s.setResult("OK");

        try {
            Hobby h = mapper.readValue(hobby,Hobby.class);
            services.addHobby(h);
        } catch (Exception e) {
            e.printStackTrace();
            s.setResult("FAIL");
            s.setMessage(e.getMessage());
        }

        return s;
    }

    @RequestMapping("/hobbies")
    public List<Hobby> list() {
        List<Hobby> hobbies = services.fetchAllHobbies();
        for(Hobby h: hobbies) {
            for(Person p: h.getPersons()) {
                p.setHobbies(null);
            }
        }

        return hobbies;
    }

    @RequestMapping(value = "hobbies/{id}", method = RequestMethod.DELETE)
    public Status deleteHobby(@PathVariable Long id) {
        Status s = new Status();
        s.setResult("OK");

        try {
            services.deleteHobby(id);
        } catch (Exception e) {
            e.printStackTrace();
            s.setResult("FAIL");
            s.setMessage(e.getMessage());
        }

        return s;
    }

    @RequestMapping(value = "hobbies/{id}", method = RequestMethod.PUT)
    public Status editHobby(@RequestBody String hobby, @PathVariable Long id) {
        Status s = new Status();
        s.setResult("OK");

        try {
            Hobby h = mapper.readValue(hobby,Hobby.class);
            services.updateHobby(h);
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
