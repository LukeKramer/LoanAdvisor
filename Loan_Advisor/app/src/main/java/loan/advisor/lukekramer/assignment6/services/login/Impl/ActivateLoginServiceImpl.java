package com.example.lukekramer.assignment6.services.login.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.entity.Login;
import com.example.lukekramer.assignment6.repository.login.Impl.LoginRepositoryImpl;
import com.example.lukekramer.assignment6.repository.login.LoginRepository;
import com.example.lukekramer.assignment6.services.login.ActivateLoginService;

/**
 * Created by lukekramer on 08/05/16.
 */

//Justification Im using bound service because the database is being accessed and affected directly

public class ActivateLoginServiceImpl extends Service implements ActivateLoginService {


    private final IBinder localBinder = new ActivateLoginServiceLocalBinder();
    private LoginRepository loginRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }



    public class ActivateLoginServiceLocalBinder extends Binder {

        public ActivateLoginServiceImpl getService(){return ActivateLoginServiceImpl.this;}
    }

    @Override
    public String activateLoginAccount(long clientid, String username, String password) {

        if(true)
        {
            Login standardUser = new Login.Builder()
                    .Userid(clientid)
                    .Username(username)
                    .Password(password)
                    .build();


            createLogin(standardUser);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }

    }

    @Override
    public boolean isAccountActivated() {
        return loginRepository.findAll().size()>0;
    }

    @Override
    public boolean deactivateAccount() {
        int row = loginRepository.deleteAll();
        return row>0;
    }

    private Login createLogin(Login login)
    {
        loginRepository = new LoginRepositoryImpl(this.getApplicationContext());

        return loginRepository.save(login);
    }





}
