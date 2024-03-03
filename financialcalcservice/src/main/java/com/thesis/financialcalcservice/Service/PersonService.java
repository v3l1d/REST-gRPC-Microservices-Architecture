package com.thesis.financialcalcservice.Service;

import com.thesis.financialcalcservice.model.Person;
import com.thesis.financialcalcservice.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;

    private final Logger logger= LogManager.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public void savePersonIfNotExists(Person person){
        if(personRepository.findByEmail(person.getEmail())==null){
            personRepository.save(person);

        }else{
            logger.error("Person with this email already exists!");
        }
    }

    public boolean findPersonByEmail(String email){
        return personRepository.findByEmail(email) != null;
    }

}
