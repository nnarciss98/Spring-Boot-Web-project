package be.wyrthh.springbootproject.api;

import be.wyrthh.springbootproject.model.Person;
import be.wyrthh.springbootproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person") //This annotation will allow us to indicate where to send requests. For example we can choose to send a specific request to "https://site.com/something" and the class with the "@RequestMapping("something")" in that website will be the one handling the request
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    /**
     * Adds the given person object to the database.
     * @param person
     *          Person to be added to the database.
     *              The information for this person object comes from a jason file that is sent through a post request to the server.
     */
    @PostMapping //This annotation indicates that this method will be used when a post request is sent.
    //@NonNull indicates that this parameter has to be a nonnull value. @Valid is used to validate elements (whatever that means, gotta look more into that). @Valid will also  format the output of an incorrect REST call into formatted JSON instead of a blob of barely readable text.
    public void addPerson(@Valid @NonNull @RequestBody Person person){ //@RequestBody annotation takes the body of the request (the post request in this case), and puts it into the person object that is given to the method
        personService.addPerson(person);
    }

    /**
     * Returns all the person objects in the database.
     *      The request comes from a GET request sent to the server.
     * @return
     *          A list of all the person elements in the database.
     */
    @GetMapping //This annotation indicates this will be called when get requests are sent to this address
    public List<Person> getAllPersons(){
        return personService.getAllPeople();
    }

    /**
     * Search for the person with the given ID.
     * @param id
     *          ID of the person you are looking for.
     * @return
     *          The person that has the given ID.
     */
    @GetMapping(path = "{id}") //This annotation gets the "id" from the path. Whatever comes after the path in the @RequestMapping is saved under the given identifier (in this case "id") to be used in other annotations if needed
    public Person getPersonById(@PathVariable("id") UUID id){ //This annotation lets us personalise get requests, this one will be executed using the "result" from the last annotation converted to UUID and passed as a parameter in this method
        return personService.getPersonByID(id).orElse(null); //Here at the .orElse() you could also send a 404 page or a custom message to the client
    }

    /**
     * Update the person with the given ID.
     * @param id
     *          ID of the person that will be updated.
     * @param person
     *          Person object containing the information that needs to be updated.
     */
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person){
        personService.updatePerson(id, person);
    }
    /**
     * Delete the person with the given ID.
     * @param id
     *          ID of the person that needs to be deleted.
     */
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
}
