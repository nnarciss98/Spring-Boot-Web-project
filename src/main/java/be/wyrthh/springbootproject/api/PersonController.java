package be.wyrthh.springbootproject.api;

import be.wyrthh.springbootproject.model.Person;
import be.wyrthh.springbootproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addPerson(@RequestBody Person person){ //@RequestBody annotation takes the body of the request (the post request in this case), and puts it into the person object that is given to the method
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
}
