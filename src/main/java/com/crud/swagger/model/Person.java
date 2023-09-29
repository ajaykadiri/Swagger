package com.crud.swagger.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String Fname;
    private String Lname;
    private String uname;
    private String Passwd;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return id == person.id && Objects.equals(Fname, person.Fname) && Objects.equals(Lname, person.Lname) && Objects.equals(uname, person.uname) && Objects.equals(Passwd, person.Passwd);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, Fname, Lname, uname, Passwd);
//    }
}
