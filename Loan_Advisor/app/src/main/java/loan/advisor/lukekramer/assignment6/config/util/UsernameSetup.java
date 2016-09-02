package com.example.lukekramer.assignment6.config.util;

/**
 * Created by lukekramer on 08/05/16.
 */
public class UsernameSetup {


    public static String createUsername(String fname,String lname,long clintid){


        char[] last = lname.toCharArray();

        String id = ""+clintid;

        String username = fname+last[0]+last[1]+last[2]+"@"+id;


        //return "Account number "+bankAccountNumber+" Exsists ";
        return username;

    }
}
