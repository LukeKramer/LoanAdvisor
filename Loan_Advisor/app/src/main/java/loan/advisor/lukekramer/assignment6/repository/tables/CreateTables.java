package com.example.lukekramer.assignment6.repository.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.assignment6.databases.DBConstants;

/**
 * Created by TNZ on 22/04/16.
 */


public class CreateTables extends SQLiteOpenHelper {

    public static final String TABLE_SETTINGS_Person = "person";
    public static final String TABLE_SETTINGS_Loan = "loan";
    public static final String TABLE_SETTINGS_Results = "loanResults";
    public static final String TABLE_SETTINGS_Login = "login";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MAXLOANAMOUNT = "max_loan_amount";
    public static final String COLUMN_MINLOANAMOUNT = "min_loan_amount";

    public static final String COLUMN_FNAME = "firstname";
    public static final String COLUMN_LNAME = "lastname";
    public static final String COLUMN_NUMBER = "phonenumber";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_INCOME = "income";

    public static final String COLUMN_CLIENT_ID = "clientID";
    public static final String COLUMN_LOAN_ID = "loanID";
    public static final String COLUMN_LOAN_STATUS = "status";
    public static final String COLUMN_TIMESTAMP = "date";

    public static final String COLUMN_USERID = "userid";// = client id
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_CREATE_Results = " CREATE TABLE IF NOT EXISTS "
            + TABLE_SETTINGS_Results + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CLIENT_ID + " TEXT NOT NULL , "
            + COLUMN_LOAN_ID + " TEXT NOT NULL , "
            + COLUMN_LOAN_STATUS + " TEXT NOT NULL , "
            + COLUMN_TIMESTAMP + " TEXT NOT NULL);";

    private static final String DATABASE_CREATE_Person = " CREATE TABLE IF NOT EXISTS "
            + TABLE_SETTINGS_Person + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FNAME + " TEXT NOT NULL , "
            + COLUMN_LNAME + " TEXT NOT NULL , "
            + COLUMN_NUMBER + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL , "
            + COLUMN_INCOME + " TEXT NOT NULL );";


    private static final String DATABASE_CREATE_Loan= " CREATE TABLE IF NOT EXISTS "
            + TABLE_SETTINGS_Loan + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MAXLOANAMOUNT + " TEXT NOT NULL , "
            + COLUMN_MINLOANAMOUNT+ " TEXT NOT NULL);";

    private static final String DATABASE_CREATE_Login = " CREATE TABLE IF NOT EXISTS "
            + TABLE_SETTINGS_Login + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERID + " TEXT NOT NULL , "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public CreateTables(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void createTables(){
        getReadableDatabase().execSQL(DATABASE_CREATE_Loan);
        getReadableDatabase().execSQL(DATABASE_CREATE_Person);
        getReadableDatabase().execSQL(DATABASE_CREATE_Results);
        getReadableDatabase().execSQL(DATABASE_CREATE_Login);
    }

    public void resetDatabase(){
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS_Loan);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS_Person);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS_Results);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS_Login);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        resetDatabase();
        onCreate(db);
    }
}
