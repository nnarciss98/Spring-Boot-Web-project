package be.wyrthh.springbootproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {

    //This annotation will allow us to work with data in jason format, more specifically to map values from jason type to object parameters or vice-versa
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("name")
    private final String name;

    //The @JasonProperty annotation allows us to work with json format and know how to map the values to the correct properties
    public Person(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Get the person ID.
     * @return
     *          The ID of this person object.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the person name.
     * @return
     *          The name of this person object.
     */
    public String getName() {
        return name;
    }
}
