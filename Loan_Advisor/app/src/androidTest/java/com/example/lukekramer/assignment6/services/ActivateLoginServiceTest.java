package com.example.lukekramer.assignment6.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.login.Impl.ActivateLoginServiceImpl;

import junit.framework.Assert;

/**
 * Created by lukekramer on 08/05/16.
 */
public class ActivateLoginServiceTest extends AndroidTestCase {

    private ActivateLoginServiceImpl activateLoginService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ActivateLoginServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ActivateLoginServiceImpl.ActivateLoginServiceLocalBinder binder
                    = (ActivateLoginServiceImpl.ActivateLoginServiceLocalBinder)service;
            activateLoginService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testActivateAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();


        String activate = activateLoginService.activateLoginAccount(1,"LukeKram","DarthVader");
        Assert.assertEquals("ACTIVATED",activate);


        // this.getContext().unbindService(connection);

    }

    public void testIsAccountActivated() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateLoginService.activateLoginAccount(1,"LukeKram","DarthVader");
        Boolean activated = activateLoginService.isAccountActivated();
        Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivatedAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateLoginService.activateLoginAccount(1,"LukeKram","DarthVader");

        Boolean deactivated = activateLoginService.deactivateAccount();
        Assert.assertTrue("NOTACTIVATED",deactivated);

    }
}
