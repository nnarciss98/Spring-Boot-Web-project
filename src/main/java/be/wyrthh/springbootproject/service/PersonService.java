package be.wyrthh.springbootproject.service;

import be.wyrthh.springbootproject.dao.PersonDao;
import be.wyrthh.springbootproject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;


    @Autowired //Automatically looks up the needed beans in the bean pool (if any bean is needed to execute the constructor)
    public PersonService(@Qualifier("Dao") PersonDao personDao){
        this.personDao = personDao;
    }

    /**
     * Adds a new person to the database.
     * @param person
     *          Person to be added to the database.
     * @return
     *          If the person is added successfully to the database return 1, else return 0.
     */
    public int addPerson(Person person){
        return this.personDao.insertPerson(person);
    }

    /**
     * Returns a list with all the people in the database.
     * @return
     *          A list with all the people in the database.
     */
    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    /**
     * Search in the database for a person with the given ID, and if there exists one, return that person.
     * @param id
     *          ID of the person you are looking for.
     * @return
     *          The person with the given ID, if it exists.
     */
    public Optional<Person> getPersonByID(UUID id){
        return personDao.selectPersonByID(id);
    }

    /**
     * Update the person with the given ID with the data from the given person.
     * @param id
     *          ID of the person that will be updated.
     * @param person
     *          Person object with the updated data
     * @return
     *          If the person is updated successfully return 1, else return 0.
     */
    public int updatePerson(UUID id, Person person){
        return personDao.updatePersonByID(id, person);
    }

    /**
     * Delete the person with the given ID.
     * @param id
     *          ID of the person that needs to be deleted.
     * @return
     *          If the person is deleted successfully return 1, else return 0.
     */
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

}
