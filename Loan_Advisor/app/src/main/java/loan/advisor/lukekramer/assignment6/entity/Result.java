package com.example.lukekramer.assignment6.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lukekramer on 16/04/16.
 */
public class Result implements Serializable {

    private long id;
    private long clientid;
    private long loanid;
    private String status;
    private long date;


    private Result(){}

    public long getId() {
        return id;
    }

    public long getClientid() {
        return clientid;
    }

    public long getLoanid(){return loanid;}

    public String getStatus() {
        return status;
    }

    public long getDate() {
        return date;
    }

    public Result(Builder Builder)
    {
        this.id = Builder.id;
        this.clientid=Builder.clientid;
        this.loanid=Builder.loanid;
        this.status = Builder.status;
        this.date = Builder.date;
    }

    public static class Builder{

        private long id;
        private long clientid;
        private long loanid;
        private String status;
        private long date;

        public Builder ID(long value)
        {
            this.id = value;
            return this;
        }

        public Builder ClientID(long value)
        {
            this.clientid = value;
            return this;
        }

        public Builder LoanID(long value){

            this.loanid = value;
            return this;

        }

        public Builder Status(String value)
        {
            this.status = value;
            return this;
        }

        public Builder Date(Long value)
        {
            Date c = new Date(System.currentTimeMillis());
            Long dateValue = c.getTime();

            this.date = dateValue;
            value = this.date;
            return this;
        }

        public Builder copy(Result value){
            this.id=value.id;
            this.clientid=value.clientid;
            this.loanid = value.loanid;
            this.status=value.status;
            this.date=value.date;

            return this;
        }

        public Result Build(){return new Result(this);}



    }
}
