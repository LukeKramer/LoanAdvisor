package com.example.lukekramer.assignment6.domain.client;

import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.interfaces.CreatePerson;

/**
 * Created by student on 2016/03/31.
 */
public class Clientfactory implements CreatePerson {

    public Person getPerson(long id, long income, String fname, String lname, String phone, String email) {

        Person person = new Person.Builder()
                .Id(id)
                .Income(income)
                .FirstName(fname)
                .LastName(lname)
                .PhoneNumber(phone)
                .Email(email)
                .build();


        return person;
    }
}
