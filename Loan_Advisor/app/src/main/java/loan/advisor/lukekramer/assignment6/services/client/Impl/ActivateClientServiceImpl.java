package com.example.lukekramer.assignment6.services.client.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import com.example.lukekramer.assignment6.repository.person.PersonRepository;
import com.example.lukekramer.assignment6.services.client.ActivateAddClientService;

import static android.util.Log.ASSERT;

/**
 * Created by lukekramer on 09/05/16.
 */

//The Handling of the result actions is done by an IntentService on the background thread
public class ActivateClientServiceImpl extends IntentService implements ActivateAddClientService {



    private static final String TAG = "AddClient";

    private static final String ACTION_ADD = "com.example.lukekramer.assignment6.services.client.action.ACTION_ADD";
    private static final String EXTRA_ADD = "com.example.lukekramer.assignment6.services.client.action.EXTRA_ADD";

    private static final String ACTION_UPDATE = "com.example.lukekramer.assignment6.services.client.action.ACTION_UPDATE";
    private static final String EXTRA_UPDATE = "com.example.lukekramer.assignment6.services.client.action.EXTRA_UPDATE";


    private static ActivateClientServiceImpl service = null;

    public ActivateClientServiceImpl() {
        super("ActivateClientServiceImpl");

    }

    public static ActivateClientServiceImpl getInstance() {
        if (service == null)
            service = new ActivateClientServiceImpl();

        return service;
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {

                    final Person client = (Person) intent.getSerializableExtra(EXTRA_ADD);
                    postClient(client);
                    //Toast.makeText(this.getBaseContext(), "Intent Detected.", Toast.LENGTH_LONG).show();
                    Log.i(TAG,"ClientAdded");
                    System.out.println(ASSERT);


            }
            else if(ACTION_UPDATE.equals(action))
            {
                final Person client =(Person) intent.getSerializableExtra(EXTRA_UPDATE);
                clientUpdate(client);
            }
        }

    }

    @Override
    public void addClient(Context context, Person client) {
        Intent intent = new Intent(context, ActivateClientServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, client);
        context.startService(intent);
    }

    @Override
    public void updateClient(Context context, Person client) {

        Intent intent = new Intent(context, ActivateClientServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, client);
        context.startService(intent);

    }

    private void clientUpdate(Person client)
    {
        PersonRepository personRepository= new PersonRepositoryImpl(getBaseContext());
        personRepository.update(client);
    }

    private void postClient(Person client) {
        PersonRepository personRepository= new PersonRepositoryImpl(getBaseContext());
        //POST and Save Local
        personRepository.save(client);
    }

    private void deleteALL(){

        PersonRepository personRepository= new PersonRepositoryImpl(getBaseContext());

        personRepository.deleteAll();


    }
}
