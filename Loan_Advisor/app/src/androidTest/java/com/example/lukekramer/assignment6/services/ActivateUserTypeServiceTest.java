package com.example.lukekramer.assignment6.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.usertype.Impl.ActivateUserTypeServiceImpl;

import junit.framework.Assert;

/**
 * Created by lukekramer on 08/05/16.
 */
public class ActivateUserTypeServiceTest extends AndroidTestCase {

    private ActivateUserTypeServiceImpl activateUserTypeService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ActivateUserTypeServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ActivateUserTypeServiceImpl.ActivateUserTypeServiceLocalBinder binder
                    = (ActivateUserTypeServiceImpl.ActivateUserTypeServiceLocalBinder) service;
            activateUserTypeService = binder.getService();

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


        String activate = activateUserTypeService.activateUserTypeAccount(100000);
        Assert.assertEquals("ACTIVATED",activate);

    }


}
