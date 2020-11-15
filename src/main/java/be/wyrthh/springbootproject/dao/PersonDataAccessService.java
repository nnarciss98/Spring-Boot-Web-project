package be.wyrthh.springbootproject.dao;

import be.wyrthh.springbootproject.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Dao") // You can also use @Component. The string in the parenthesis works like an identifier in the bean pool. If anywhere in the code, a bean with that identifier is asked for, it will use this instance of the bean (useful when you have different implementations of the same interface)
public class PersonDataAccessService implements PersonDao {

    private static List<Person> database = new ArrayList<>();

    /**
     * Insert a new person object in the database.
     * @param id
     *          ID of the person to be added to the database.
     * @param person
     *          Person to be added to the database.
     * @return
     *          If the person is added successfully return 1, else return 0.
     */
    @Override
    public int insertPerson(UUID id, Person person) {
        database.add(new Person(id, person.getName()));
        return 1;
    }

    /**
     * Returns a list with all the people in the database.
     * @return
     *          A list with all the person objects in the database.
     */
    @Override
    public List<Person> selectAllPeople() {
        return database;
    }


    /**
     * Go through all the persons in the database and return the first person with the given ID, if there exists one.
     * @param id
     *          ID of the person you are looking for in the database.
     * @return
     *          The first person in the database with the given ID, if there exists one.
     */
    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return database.stream().filter(person -> person.getId().equals(id)).findFirst();
    }


    /**
     * Updates the person object with the given ID.
     * @param id
     *          ID of the person that needs to be updated.
     * @param person
     *          New object person with the updated elements
     *          (since this is a small project where person has only a field to learn how this works, there is no point on going through the whole update-each-individual-element-at-a-time process)
     * @return
     *          If the person is updated successfully return 1, else return 0.
     */
    @Override
    public int updatePersonByID(UUID id, Person person) {
        return selectPersonByID(id)
                .map(p -> {
                    int indexOfPersonToDelete = database.indexOf(person);
                    if (indexOfPersonToDelete == 1) {
                        database.set(indexOfPersonToDelete, person);
                        return 1;
                    }
                    return 0;
                }).orElse(0); //Here you could send the client to a 404 page or a personalised message
    }

    /**
     * Checks whether there exists a person in the database with the given ID, if there exists one it will pe deleted.
     * @param id
     *          ID of the person that will be deleted from the database.
     * @return
     *          If the person with the given ID doesn't exist in the database returns 0, else it deletes the person and returns 1.
     */
    @Override
    public int deletePersonById(UUID id) {
        if (selectPersonByID(id).isPresent()) {
            database.remove(selectPersonByID(id));
            return 1;
        }
        return 0;
    }


}
