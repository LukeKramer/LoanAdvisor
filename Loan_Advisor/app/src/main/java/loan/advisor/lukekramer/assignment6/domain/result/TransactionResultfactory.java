package com.example.lukekramer.assignment6.domain.result;

import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.interfaces.CreateResult;

/**
 * Created by lukekramer on 16/04/16.
 */
public class TransactionResultfactory implements CreateResult {

    private Long value;
    @Override
    public Result getResult(long clientid, long loanid ,String status) {

        Result result = new Result.Builder()
                .ID(1)
                .Status(status)
                .ClientID(clientid)
                .LoanID(loanid)
                .Date(value)
                .Build();
        return result;
    }
}
