package com.example.lukekramer.assignment6.repository;

import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.entity.Login;
import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.repository.Result.Impl.ResultRepositoryImpl;
import com.example.lukekramer.assignment6.repository.Result.ResultRepository;
import com.example.lukekramer.assignment6.repository.loan.Impl.LoanRepositoryImpl;
import com.example.lukekramer.assignment6.repository.loan.LoanRepository;
import com.example.lukekramer.assignment6.repository.login.Impl.LoginRepositoryImpl;
import com.example.lukekramer.assignment6.repository.login.LoginRepository;
import com.example.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import com.example.lukekramer.assignment6.repository.person.PersonRepository;
import com.example.lukekramer.assignment6.repository.tables.CreateTables;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by lukekramer on 20/04/16.
 */
public class DataBaseTablesRepositoryTest extends AndroidTestCase {
    private static final String TAG_Loan="Loan TEST";
    private static final String TAG_Person="Person TEST";
    private static final String TAG_Result="Result TEST";
    private static final String TAG_Login="Login TEST";
    private Long id;
    private Long loanid;
    private Long resultid;
    private Long loginid;

    public void testData() throws Exception {
        //NB!!IMPORTANT
        //*****
        // Run the table creator code segment once to create tables then comment
        // this code segment out to avoid tables being reset(droped the recreated) and data being lost
        // when running the testCase multiple times!!
        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();
        //******************************************

        //Client Table
        //Create a client
        PersonRepository personRepository = new PersonRepositoryImpl(this.getContext());


        Person standardPerson = new Person.Builder()
                .Email("Luke@gmail.com")
                .FirstName("Luke")
                .Income(100000)
                .LastName("Kramer")
                .PhoneNumber("98979878")
                .build();

        standardPerson=personRepository.save(standardPerson);
        id = standardPerson.getId();
        Assert.assertNotNull(TAG_Person+" CREATE",standardPerson);

        //READ (findALL) Clients
        Set<Person> clients = personRepository.findAll();
        Assert.assertTrue(TAG_Person+" READ ALL",clients.size()>0);

        //READ (find) client
        Person clientEntity = personRepository.findById(id);
        Assert.assertNotNull(TAG_Person+" READ ENTITY",clientEntity);

        //UPDATE Client
        Person updateClient = new Person.Builder()
                .copy(clientEntity)
                .Email("TEST")
                .build();
        personRepository.update(updateClient);
        Person newClient = personRepository.findById(id);
        Assert.assertEquals(TAG_Person+ " UPDATE ENTITY","TEST",newClient.getEmailAddress());

        //DELETE Loan
        //personRepository.delete(newClient);
       // Person deletedClient = personRepository.findById(id);
        //Assert.assertNull(TAG_Loan+" DELETE",deletedClient);

        int totalrows = personRepository.deleteAll();

        Assert.assertTrue(totalrows > 0);




        //*******************************************************

        //Loan Table
        //Create a loan
        LoanRepository loanRepo = new LoanRepositoryImpl(this.getContext());

        Loan standardLoan = new Loan.Builder()
                                .maxLoanAmount((long)100000)
                                .minLoanAmount((long)100)
                                .build();
        standardLoan = loanRepo.save(standardLoan);
        loanid = standardLoan.getId();
        Assert.assertNotNull(TAG_Loan+" CREATE",standardLoan);

        //READ (findALL) Loans
        Set<Loan> Loans = loanRepo.findAll();
        Assert.assertTrue(TAG_Loan+" READ ALL",Loans.size()>0);

        //READ (find) Loan
        Loan loanEntity = loanRepo.findById(loanid);
        Assert.assertNotNull(TAG_Loan+" READ ENTITY",loanEntity);

        //UPDATE Loan
        Loan updateLoan = new Loan.Builder()
                .copy(loanEntity)
                .minLoanAmount((long)1000)
                .build();

        loanRepo.update(updateLoan);
        Loan newLoan = loanRepo.findById(loanid);
        Assert.assertEquals(TAG_Loan+ " UPDATE ENTITY",1000,newLoan.getMinAmount());

        //DELETE Loan

        loanRepo.delete(newLoan);
        Loan deletedLoan = loanRepo.findById(loanid);
        Assert.assertNull(TAG_Loan+" DELETE",deletedLoan);


        //*******************************************************

        //Result Table
        //CreateResult
        ResultRepository resultRepository = new ResultRepositoryImpl(this.getContext());
        Long value = null;
        Result standardResult = new Result.Builder()
                .ClientID(1)
                .LoanID(2)
                .Status("Approved")
                .Date(value)
                .Build();


        standardResult = resultRepository.save(standardResult);
        resultid = standardResult.getId();
        Assert.assertNotNull(TAG_Result+" CREATE",standardResult);
        //Returns current Date;
        Assert.assertNotNull(standardResult.getDate());

        //READ (findALL) Results
        Set<Result> results = resultRepository.findAll();
        Assert.assertTrue(TAG_Result+" READ ALL",results.size()>0);

        //READ (find) Result
        Result resultEntity = resultRepository.findById(resultid);
        Assert.assertNotNull(TAG_Result+" READ ENTITY",resultEntity);

        //UPDATE Result

        Result updateResult = new Result.Builder()
                .copy(resultEntity)
                .Status("Declined")
                .Build();
        resultRepository.update(updateResult);
        Result newresult = resultRepository.findById(resultid);
        Assert.assertEquals(TAG_Result+" UPDATE ENTITY","Declined",newresult.getStatus());

        // DELETE Result
        resultRepository.delete(newresult);
        Result deletedResult = resultRepository.findById(resultid);
        Assert.assertNull(TAG_Result+" DELETE",deletedResult);

        //Login Table
        //Create Login
        LoginRepository loginRepository = new LoginRepositoryImpl(this.getContext());
        Login standardLogin = new Login.Builder()
                .Userid(1)
                .Username("LukeKram")
                .Password("DarthVader")
                .build();

        standardLogin = loginRepository.save(standardLogin);
        loginid = standardLogin.getId();
        Assert.assertNotNull(TAG_Login+"CREATE",standardLogin);

        //Find all users
        Set<Login> users = loginRepository.findAll();
        Assert.assertTrue(TAG_Login+"READ ALL",users.size()>0);

        //Find by id
        Login loginUser = loginRepository.findById(loginid);
        Assert.assertNotNull(TAG_Login+" READ ENTITY",loginUser);

        //Update user Login

        Login userEntity = new Login.Builder().copy(standardLogin)
                .Password("Skywalker")
                .build();
        loginRepository.update(userEntity);

        Login newUser = loginRepository.findById(loginid);
        Assert.assertEquals(TAG_Login+" UPDATE ENTITY","Skywalker",newUser.getPassword());

        //Delete User Login

        loginRepository.delete(newUser);
        Login deleteLogin = loginRepository.findById(loginid);
        Assert.assertNull(TAG_Login+" DELETE",deleteLogin);




        //*******************************************************

    }
}
