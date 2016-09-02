package com.example.lukekramer.assignment6.interfaces;

import com.example.lukekramer.assignment6.entity.Login;

/**
 * Created by lukekramer on 08/05/16.
 */
public interface CreateLogin {

    Login createLogin(long userid,String username,String password);

}
