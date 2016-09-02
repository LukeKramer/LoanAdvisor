package com.example.lukekramer.assignment6.services.login;

/**
 * Created by lukekramer on 08/05/16.
 */
public interface ActivateLoginService {

    String activateLoginAccount(long clientid, String username, String password);

    boolean isAccountActivated();

    boolean deactivateAccount();
}
