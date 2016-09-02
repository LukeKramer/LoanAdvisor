package com.example.lukekramer.assignment6.services;

import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import com.example.lukekramer.assignment6.repository.person.PersonRepository;
import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.client.ActivateAddClientService;
import com.example.lukekramer.assignment6.services.client.Impl.ActivateClientServiceImpl;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by lukekramer on 10/05/16.
 */
public class ActivateClientServiceTest extends AndroidTestCase {

    private static final String TAG_Person="Person TEST";



    public void testClientIntentService() throws Exception {


        //create tables
        CreateTables createTables = new CreateTables(this.getContext());
        //createTables.resetDatabase();
        //createTables.createTables();

        //GetContext


       PersonRepository personRepository = new PersonRepositoryImpl(this.getContext());


        //Add Client

        ActivateAddClientService clientService =  ActivateClientServiceImpl.getInstance();

        Person standardPerson = new Person.Builder()
                .Email("Luke@gmail.com")
                .FirstName("Chase")
                .Income(100000)
                .LastName("Kramer")
                .PhoneNumber("98979878")
                .build();

        // client using intentservice

        clientService.addClient(this.mContext, standardPerson);
        Assert.assertNotNull(standardPerson);

        //Update Client

       Person updateClient = new Person.Builder()
               .copy(standardPerson)
               .Email("TEST")
                .build();

        //update client using intentservice
        clientService.updateClient(this.mContext,updateClient);

        Assert.assertNotNull(updateClient);


        Thread.sleep(5000);
        //READ (findALL) Clients
        Set<Person> clients = personRepository.findAll();
        Assert.assertTrue(TAG_Person+" READ ALL",clients.size()>0);




    }
}
