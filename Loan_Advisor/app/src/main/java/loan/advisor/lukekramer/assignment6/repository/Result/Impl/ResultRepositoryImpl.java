package com.example.lukekramer.assignment6.repository.Result.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.assignment6.databases.DBConstants;
import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.repository.Result.ResultRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 21/04/16.
 */
public class ResultRepositoryImpl extends SQLiteOpenHelper implements ResultRepository {

    public static final String TABLE_SETTINGS = "loanResults";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CLIENT_ID = "clientID";
    public static final String COLUMN_LOAN_ID = "loanID";
    public static final String COLUMN_LOAN_STATUS = "status";
    public static final String COLUMN_TIMESTAMP = "date";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SETTINGS + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CLIENT_ID + " TEXT NOT NULL , "
            + COLUMN_LOAN_ID + " TEXT NOT NULL , "
            + COLUMN_LOAN_STATUS + " TEXT NOT NULL , "
            + COLUMN_TIMESTAMP + " TEXT NOT NULL);";

    public ResultRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        onCreate(db);

    }

    @Override
    public Result findById(Long id) {

       SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SETTINGS,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CLIENT_ID,
                        COLUMN_LOAN_ID,
                        COLUMN_LOAN_STATUS,
                        COLUMN_TIMESTAMP},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Result result = new Result.Builder()
                    .ID(cursor.getLong(0))
                    .ClientID(cursor.getLong(1))
                    .LoanID(cursor.getLong(2))
                    .Status(cursor.getString(3))
                    .Date(cursor.getLong(4))
                    .Build();

            return result;
        } else {
            return null;
        }

    }

    @Override
    public Result save(Result entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CLIENT_ID, entity.getClientid());
        values.put(COLUMN_LOAN_ID, entity.getLoanid());
        values.put(COLUMN_LOAN_STATUS, entity.getStatus());
        values.put(COLUMN_TIMESTAMP, entity.getDate());
        long id = db.insertOrThrow(TABLE_SETTINGS, null, values);
        Result insertedEntity = new Result.Builder()
                .copy(entity)
                .ID(new Long(id))
                .Build();
        return insertedEntity;
    }

    @Override
    public Result update(Result entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CLIENT_ID, entity.getClientid());
        values.put(COLUMN_LOAN_ID, entity.getLoanid());
        values.put(COLUMN_LOAN_STATUS, entity.getStatus());
        values.put(COLUMN_TIMESTAMP, entity.getDate());
        db.update(
                TABLE_SETTINGS,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Result delete(Result entity) {
        open();
        db.delete(
                TABLE_SETTINGS,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Result> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SETTINGS;
        Set<Result> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Result result = new Result.Builder()
                        .ID(cursor.getLong(0))
                        .ClientID(cursor.getLong(1))
                        .LoanID(cursor.getLong(2))
                        .Status(cursor.getString(3))
                        .Date(cursor.getLong(4))
                        .Build();
                settings.add(result);
            } while (cursor.moveToNext());
        }

        return settings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_SETTINGS,null,null);
        //close();
        return rowsDeleted;
    }
}
