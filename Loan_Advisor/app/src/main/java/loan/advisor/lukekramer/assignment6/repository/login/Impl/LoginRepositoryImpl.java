package com.example.lukekramer.assignment6.repository.login.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.assignment6.databases.DBConstants;
import com.example.lukekramer.assignment6.entity.Login;
import com.example.lukekramer.assignment6.repository.login.LoginRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 08/05/16.
 */
public class LoginRepositoryImpl extends SQLiteOpenHelper implements LoginRepository{

    public static final String TABLE_SETTINGS = "login";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERID = "userid";// = client id
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SETTINGS + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERID + " TEXT NOT NULL , "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public LoginRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

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
    public Login findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SETTINGS,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERID,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Login login = new Login.Builder()
                    .ID(cursor.getLong(0))
                    .Userid(cursor.getLong(1))
                    .Username(cursor.getString(2))
                    .Password(cursor.getString(3))
                    .build();
            return login;

        } else {

            return null;
        }
    }

    @Override
    public Login save(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, entity.getUserid());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        long id = db.insertOrThrow(TABLE_SETTINGS, null, values);
        Login insertEntity = new Login.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public Login update(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USERID, entity.getUserid());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        db.update(
                TABLE_SETTINGS,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Login delete(Login entity) {
        open();
        db.delete(
                TABLE_SETTINGS,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Login> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SETTINGS;
        Set<Login> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Login login = new Login.Builder()
                        .ID(cursor.getLong(0))
                        .Userid(cursor.getLong(1))
                        .Username(cursor.getString(2))
                        .Password(cursor.getString(3))
                        .build();
                settings.add(login);
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
