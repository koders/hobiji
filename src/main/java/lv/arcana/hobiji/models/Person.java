package lv.arcana.hobiji.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_hobby", joinColumns = {
            @JoinColumn(name = "hobby_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "person_id",
                    nullable = false) })
    private List<Hobby> hobbies;

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

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
