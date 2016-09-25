package lv.arcana.hobiji.services;

import lv.arcana.hobiji.models.Hobby;
import lv.arcana.hobiji.models.Person;

import java.util.List;

public interface Services {

    public void addHobby(Hobby hobby);
    public List<Hobby> fetchAllHobbies();
    public Hobby findHobby(Long id);
    public void updateHobby(Hobby hobby);
    public void deleteHobby(Long id);

    public void addPerson(Person person);
    public List<Person> fetchAllPersons();
    public Person findPerson(Long id);
    public void updatePerson(Person person);
    public void deletePerson(Long id);

}
