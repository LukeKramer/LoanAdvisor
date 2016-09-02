package com.example.lukekramer.assignment6.entity;

import java.io.Serializable;

/**
 * Created by student on 2016/03/30.
 */
public class Person implements Serializable {

    private long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private long income;

    private Person(){}


    public long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public long getIncome() {
        return income;
    }

    public void editIncome(long value)
    {
        this.income = value;
    }

    @Override
    public String toString() {
        return "Client id: "+getId()+"\nSalary: "+getIncome()+"\nFirst Name:"+getFirstName()+"\nLast Name: "+getLastName()+"\nPhone Number: "+getPhoneNumber()+"\nEmail Address: "+getEmailAddress();
    }

    public Person(Builder builder) {
        this.id=builder.id;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.phoneNumber=builder.phoneNumber;
        this.emailAddress=builder.emailAddress;
        this.income = builder.income;
    }



    public static class Builder
    {

        private long id;

        private String firstName;

        private String lastName;

        private String phoneNumber;

        private String emailAddress;

        private long income;

        public Builder Id(long value)
        {
            this.id = value;
            return this;
        }


        public Builder FirstName(String value)
        {
            this.firstName = value;
            return this;
        }

        public Builder LastName(String value)
        {
            this.lastName = value;
            return this;
        }

        public Builder PhoneNumber(String value)
        {
            this.phoneNumber = value;
            return this;
        }

        public Builder Email(String value)
        {
            this.emailAddress = value;
            return this;
        }

        public Builder Income(long value)
        {
            this.income = value;
            return this;
        }

        public Builder copy(Person value){
            this.id=value.id;
            this.firstName=value.firstName;
            this.lastName=value.lastName;
            this.phoneNumber=value.phoneNumber;
            this.emailAddress=value.emailAddress;
            this.income = value.income;

            return this;
        }



        public Person build()
        {
            return new Person(this);
        }

    }




}
