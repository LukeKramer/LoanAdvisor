package com.example.lukekramer.assignment6.config.util;


import org.mindrot.jbcrypt.BCrypt;

/*
 * Created by lukekramer on 08/05/16.
 */
public class UserpasswordSetup {

    public static boolean checkPassword(String password) {


        return BCrypt.checkpw(password, BCrypt.gensalt());
    }


}
