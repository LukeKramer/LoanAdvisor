package com.example.lukekramer.assignment6.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.repository.Result.Impl.ResultRepositoryImpl;
import com.example.lukekramer.assignment6.repository.Result.ResultRepository;
import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.result.ActivateResultService;
import com.example.lukekramer.assignment6.services.result.Impl.ActivateResultServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by lukekramer on 10/05/16.
 */
public class ActivateResultServiceTest extends AndroidTestCase {

    private static final String TAG_Result="Result TEST";

    public void testResultIntentService() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();


        Context context = getContext();

        ResultRepository resultRepository = new ResultRepositoryImpl(this.getContext());

        ActivateResultService resultService = ActivateResultServiceImpl.getInstance();

        Long value = null;
        Result standardResult = new Result.Builder()
                .ClientID(1)
                .LoanID(2)
                .Status("Approved")
                .Date(value)
                .Build();
        resultService.addResult(context,standardResult);

        Assert.assertNotNull(standardResult);

        Thread.sleep(5000);

        Set<Result> results = resultRepository.findAll();
        Assert.assertTrue(TAG_Result+" READ ALL",results.size()>0);



    }
}
