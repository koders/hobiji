package lv.arcana.hobiji.services;

import lv.arcana.hobiji.dao.BaseDAO;
import lv.arcana.hobiji.models.Hobby;
import lv.arcana.hobiji.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicesImpl implements Services{

    @Autowired
    BaseDAO dao;

    @Override
    public void addHobby(Hobby hobby) {
        dao.saveHobby(hobby);
    }

    @Override
    public List<Hobby> fetchAllHobbies() {
        return dao.allHobbies();
    }

    @Override
    public Hobby findHobby(Long id) {
        return dao.findHobby(id);
    }

    @Override
    public void updateHobby(Hobby hobby) {
        dao.updateHobby(hobby);
    }

    @Override
    public void deleteHobby(Long id) {
        dao.deleteHobby(id);
    }

    @Override
    public void addPerson(Person person) {
        dao.savePerson(person);
    }

    @Override
    public List<Person> fetchAllPersons() {
        return dao.allPersons();
    }

    @Override
    public Person findPerson(Long id) {
        return dao.findPerson(id);
    }

    @Override
    public void updatePerson(Person person) {
        dao.updatePerson(person);
    }

    @Override
    public void deletePerson(Long id) {
        dao.deletePerson(id);
    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }
}
