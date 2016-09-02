package com.example.lukekramer.assignment6.domain.login;

import com.example.lukekramer.assignment6.entity.Login;
import com.example.lukekramer.assignment6.interfaces.CreateLogin;

/**
 * Created by lukekramer on 08/05/16.
 */
public class LoginFactory implements CreateLogin {
    @Override
    public Login createLogin(long userid, String username, String password) {

        Login login = new Login.Builder()
                .Userid(userid)
                .Username(username)
                .Password(password)
                .build();
        return login;
    }
}
