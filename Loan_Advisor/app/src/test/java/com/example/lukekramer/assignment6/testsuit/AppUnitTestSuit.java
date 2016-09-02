package com.example.lukekramer.assignment6.testsuit;


import com.example.lukekramer.assignment6.client.ClientTest;
import com.example.lukekramer.assignment6.loan.LoanTest;
import com.example.lukekramer.assignment6.login.LoginFactoryTest;
import com.example.lukekramer.assignment6.result.ResultTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by lukekramer on 16/04/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ClientTest.class,LoanTest.class,ResultTest.class, LoginFactoryTest.class})

public class AppUnitTestSuit {
}
