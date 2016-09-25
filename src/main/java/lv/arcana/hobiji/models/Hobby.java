package lv.arcana.hobiji.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Hobby implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hobbies")
    private List<Person> persons;

    public Hobby() {
    }

    public Hobby(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @JsonIgnore
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
