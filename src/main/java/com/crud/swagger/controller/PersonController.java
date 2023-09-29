package com.crud.swagger.controller;


import com.crud.swagger.exception.ResourceNotFoundException;
import com.crud.swagger.model.Person;
import com.crud.swagger.repositories.PersonRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController("/rest")
public class PersonController {

    private PersonRepo prepo;

    public PersonController(PersonRepo prepo){ this.prepo = prepo; }

    @GetMapping("/persons")
    public List<Person> getPersons(){
        return (List<Person>) prepo.findAll();
    }

    @PostMapping("/persons")
    public void addPerson(@RequestBody Person person) {
        prepo.save(person);
    }

    @PutMapping("/persons/{id}")
    public void updatePerson(@PathVariable("id") long id, @RequestBody Person updatedPerson) {
        Optional<Person> existingPerson = prepo.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setFname(updatedPerson.getFname());
            person.setLname(updatedPerson.getLname());
            person.setUname(updatedPerson.getUname());
            person.setPasswd(updatedPerson.getPasswd());
            prepo.save(person);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable("id") long id) {
        Optional<Person> existingPerson = prepo.findById(id);

        if (existingPerson.isPresent()) {
            return new ResponseEntity<>(existingPerson.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long id){
        Optional<Person> existingPerson = prepo.findById(id);

        if (existingPerson.isPresent()){
            prepo.delete(existingPerson.get());
        return ResponseEntity.ok("Person with ID " + id + " has been deleted.");
    } else {
        return ResponseEntity.notFound().build();
    }}

    @DeleteMapping("/persons/all")
    public ResponseEntity<String> deleteAllPersons() {
        prepo.deleteAll();
        return ResponseEntity.ok("All persons have been deleted.");
    }

}
