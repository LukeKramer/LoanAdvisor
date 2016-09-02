package com.example.lukekramer.assignment6.interfaces;

import com.example.lukekramer.assignment6.entity.Person;

/**
 * Created by student on 2016/03/31.
 */
public interface CreatePerson {

    Person getPerson(long id, long income, String fname, String lname, String phone, String email);
}
