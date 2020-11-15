package be.wyrthh.springbootproject.dao;

import be.wyrthh.springbootproject.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    /**
     * Insert a person into a database.
     * @param id
     *          ID of the person to be added to the database.
     * @param person
     *          Person to be added to the database.
     * @return
     *          If the person is added successfully return 1, else return 0.
     */
    int insertPerson(UUID id, Person person);

    /**
     * Add a person without an id.
     * @param person
     *          Persona that will be added to the database.
     * @return
     *          If the person is added successfully return 1, else return 0.
     */
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    /**
     * Select all the people in the "local database" (in this example the database is a local list, since we don't need more than that to store this data).
     * @return
     *          A list of all the people in the local database.
     */
    List<Person> selectAllPeople();

    /**
     * Returns the person from the database with the given ID.
     * @param id
     *          ID of the person you are looking for in the database.
     * @return
     *          The Person with the given ID if it exists.
     */
    Optional<Person> selectPersonByID(UUID id);


    /**
     * Updates the person with the given ID.
     * @param id
     *          ID of the person that needs to be updated.
     * @param person
     *          New object person with the updated elements
     *          (since this is a small project where person has only a fiel to learn how this works, there is no point on going through the whole update-each-individual-element-at-a-time process)
     * @return
     */
    int updatePersonByID(UUID id, Person person);

    /**
     * Delete a person from the database with the given ID.
     * @param id
     *          ID of the person that will be deleted from the database.
     * @return
     *          If the person is deleted successfully return 1, else return 0.
     */
    int deletePersonById(UUID id);


}
