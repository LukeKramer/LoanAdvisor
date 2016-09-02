package com.example.lukekramer.assignment6.services.result.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.repository.Result.Impl.ResultRepositoryImpl;
import com.example.lukekramer.assignment6.services.result.ActivateResultService;

/**
 * Created by lukekramer on 10/05/16.
 */


public class ActivateResultServiceImpl extends IntentService implements ActivateResultService{



    private static final String ACTION_ADD = "com.example.lukekramer.assignment6.services.result.action.ACTION_ADD";
    private static final String EXTRA_ADD = "com.example.lukekramer.assignment6.services.result.action.EXTRA_ADD";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    private static ActivateResultServiceImpl service = null;

    public static ActivateResultServiceImpl getInstance() {
        if (service == null)
            service = new ActivateResultServiceImpl();

        return service;
    }
    public ActivateResultServiceImpl() {
        super("ActivateResultServiceImpl");


    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Result result = (Result) intent.getSerializableExtra(EXTRA_ADD);
                postClient(result);
            }
        }

    }

    @Override
    public void addResult(Context context, Result result) {

        Intent intent = new Intent(context, ActivateResultServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, result);
        context.startService(intent);
    }

    private void postClient(Result result) {

        ResultRepositoryImpl resultRepository = new ResultRepositoryImpl(getBaseContext());
        //POST and Save Local
        resultRepository.save(result);
    }
}
