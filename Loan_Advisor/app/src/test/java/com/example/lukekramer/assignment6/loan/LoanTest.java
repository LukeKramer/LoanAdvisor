package com.example.lukekramer.assignment6.loan;

import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.domain.client.Clientfactory;
import com.example.lukekramer.assignment6.domain.loan.Loanfactory;
import com.example.lukekramer.assignment6.chain.ClientChainSetup;
import com.example.lukekramer.assignment6.chain.LoanChainSetup;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by student on 2016/03/31.
 */
public class LoanTest {

    @Test
    public void testEnityLoan() throws Exception {

        Loanfactory fact = new Loanfactory();

        Loan loan = fact.getLoan();

        Assert.assertEquals(100000,loan.getMaxAmount());
        Assert.assertEquals(1000,loan.getMinAmount());

    }

    @Test
    public void testLoanApprovel() throws Exception {

        Clientfactory one = new Clientfactory();

        Person person = one.getPerson(1234,30000,"David","Keys","078-432984","keys@gmail.com");

        person.getIncome();

        String type = ClientChainSetup.getClientType(person.getIncome());

        String result = LoanChainSetup.getLoanType(type,50000);

        System.out.println(result);

        Assert.assertNotNull(result);

    }


    @Test
    public void testLoanfactory() throws Exception {


        Clientfactory one = new Clientfactory();

        Person person = one.getPerson(1234,30000,"David","Keys","078-432984","keys@gmail.com");

        person.getIncome();

        person = new Person.Builder().copy(person).Income(70000).build();

        String type = ClientChainSetup.getClientType(person.getIncome());

        System.out.println(type);

        String result = LoanChainSetup.getLoanType(type,50000);

        System.out.println(result);

        Assert.assertSame(type,"premiumwage");



    }


    @Test
    public void testLoanMaxAmountEdit() throws Exception {


        Loanfactory one = new Loanfactory();

        Loan loan = one.getLoan();

        System.out.println(loan.getMaxAmount());

        loan = new Loan.Builder().copy(loan).maxLoanAmount((long) 500000).build();

        System.out.println(loan.getMaxAmount());

        Assert.assertEquals(loan.getMaxAmount(),500000);




    }
}
