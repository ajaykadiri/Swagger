package com.crud.swagger.repositories;

import com.crud.swagger.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface PersonRepo extends JpaRepository<Person, Long> {
     Optional<Person> findByUname(String Uname);
}
