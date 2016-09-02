package com.example.lukekramer.assignment6.config.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by lukekramer on 10/05/16.
 */
public class App extends Application {

    private static Context context;
    private static App singleton;

    public void onCreate()
    {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;

    }

    public static Context getAppContext(){return App.context;}


}
