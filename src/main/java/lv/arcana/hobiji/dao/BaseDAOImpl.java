package lv.arcana.hobiji.dao;

import lv.arcana.hobiji.models.Hobby;
import lv.arcana.hobiji.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class BaseDAOImpl implements BaseDAO{

    private SessionFactory sessionFactory;

    @Override
    public void saveHobby(Hobby h) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(h);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Hobby> allHobbies() {
        Session session = this.sessionFactory.openSession();
        List<Hobby> hobbyList = session.createQuery("select h from "+ Hobby.class.getName() +" h").list();
        session.close();
        return hobbyList;
    }

    @Override
    public Hobby findHobby(Long id) {
        Session session = this.sessionFactory.openSession();
        Hobby h = (Hobby)session.get(Hobby.class, id);
        session.close();
        return h;
    }

    @Override
    public void updateHobby(Hobby h) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(h);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteHobby(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Person> persons = allPersons();
            for(Person p: persons) {
                if(p.getHobbies() != null)
                    for(Hobby h: p.getHobbies()) {
                        if(h != null && h.getId() == id) {
                            p.getHobbies().remove(h);
                            session.update(p);
                            break;
                        }
                    }
            }
            Hobby h = (Hobby)session.get(Hobby.class, id);
            session.delete(h);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        session.close();
    }



    // Persons
    @Override
    public void savePerson(Person p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> allPersons() {
        Session session = this.sessionFactory.openSession();
        List<Person> personList = session.createQuery("select p from "+ Person.class.getName() +" p").list();
        session.close();
        return personList;
    }

    @Override
    public Person findPerson(Long id) {
        Session session = this.sessionFactory.openSession();
        Person p = (Person)session.get(Person.class, id);
        session.close();
        return p;
    }

    @Override
    public void updatePerson(Person p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
        session.close();
    }

    @Override
    public void deletePerson(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Person p = (Person)session.get(Person.class, id);
        session.delete(p);
        tx.commit();
        session.close();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
