package com.example.lukekramer.assignment6.result;

import com.example.lukekramer.assignment6.chain.ClientChainSetup;
import com.example.lukekramer.assignment6.chain.LoanChainSetup;
import com.example.lukekramer.assignment6.config.util.BankingSetup;
import com.example.lukekramer.assignment6.config.util.UsernameSetup;
import com.example.lukekramer.assignment6.config.util.UserpasswordSetup;
import com.example.lukekramer.assignment6.domain.client.Clientfactory;
import com.example.lukekramer.assignment6.domain.result.TransactionResultfactory;
import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.entity.Result;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Date;

/**
 * Created by lukekramer on 16/04/16.
 */
public class ResultTest {
    @Test
    public void testName() throws Exception {

        TransactionResultfactory trans = new TransactionResultfactory();
        Result result = trans.getResult(5,2,"Approved");

        Assert.assertEquals(1,result.getId());
        Assert.assertEquals(5,result.getClientid());
        Assert.assertEquals(2,result.getLoanid());
        Assert.assertEquals("Approved",result.getStatus());

        Date date = new Date();
        //Convert Long back to Date format
        Date format = new Date(result.getDate());

        Assert.assertNotSame(date,format);


    }

    @Test
    public void testDomainEvent() throws Exception {

        Clientfactory one = new Clientfactory();

        Person person = one.getPerson(1234,30000,"David","Keys","078-432984","keys@gmail.com");

        person.getIncome();

        person = new Person.Builder().copy(person).Income(70000).build();

        String type = ClientChainSetup.getClientType(person.getIncome());


        String result = LoanChainSetup.getLoanType(type,50000);


        TransactionResultfactory transaction = new TransactionResultfactory();
        Result event = transaction.getResult(person.getId(),1,result);


        Assert.assertEquals(person.getId(),event.getClientid());

        Date date = new Date();

        System.out.println(event.getStatus());
        //Date in milli seconds
        System.out.println(event.getDate());
        //System format
        Date format = new Date(event.getDate());
        System.out.println(format);

        Assert.assertNotSame(date,format);

    }

    @Test
    public void testBank() throws Exception {

       String answer= BankingSetup.ConfirmBankAccount("123");

        Assert.assertSame("123", answer);

    }

    @Test
    public void testUsernameCreation() throws Exception {

        String Username = UsernameSetup.createUsername("Luke","Kramer",7);
        System.out.println(Username);

    }

    @Test
    public void testHashedpassword() throws Exception {

        String password = "Helloagain";

        boolean hashed = UserpasswordSetup.checkPassword(password);

        Assert.assertFalse(hashed);

    }
}
