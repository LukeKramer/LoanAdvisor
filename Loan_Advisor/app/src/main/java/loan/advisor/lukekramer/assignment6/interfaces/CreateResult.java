package com.example.lukekramer.assignment6.interfaces;

import com.example.lukekramer.assignment6.entity.Result;

/**
 * Created by lukekramer on 16/04/16.
 */
public interface CreateResult {

    Result getResult(long clientid,long loanid, String status);
}
