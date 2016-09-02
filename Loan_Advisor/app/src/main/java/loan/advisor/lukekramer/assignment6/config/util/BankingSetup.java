package com.example.lukekramer.assignment6.config.util;

import java.io.Serializable;

/**
 * Created by lukekramer on 08/05/16.
 */
public class BankingSetup implements Serializable{

    //private long bankAccountNumber;

    public static String ConfirmBankAccount(String accountnumber){

        // Research code that recieves info from bank to confirm account number exsists
        String bankAccountNumber = accountnumber;


        //return "Account number "+bankAccountNumber+" Exsists ";
        return bankAccountNumber;

    }
}
