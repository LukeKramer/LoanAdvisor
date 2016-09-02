package com.example.lukekramer.assignment6.services.client;

import android.content.Context;

import com.example.lukekramer.assignment6.entity.Person;

/**
 * Created by lukekramer on 04/05/16.
 */
public interface ActivateAddClientService {

    void addClient(Context context, Person client);

    void updateClient(Context context, Person client);

}
