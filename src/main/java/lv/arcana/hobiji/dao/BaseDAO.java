package lv.arcana.hobiji.dao;

import lv.arcana.hobiji.models.Hobby;
import lv.arcana.hobiji.models.Person;

import java.util.List;

public interface BaseDAO {

    // Hobbies
    public void saveHobby(Hobby h);
    public List<Hobby> allHobbies();
    public Hobby findHobby(Long id);
    public void updateHobby(Hobby h);
    public void deleteHobby(Long id);

    // Persons
    public void savePerson(Person p);
    public List<Person> allPersons();
    public Person findPerson(Long id);
    public void updatePerson(Person p);
    public void deletePerson(Long id);

}
