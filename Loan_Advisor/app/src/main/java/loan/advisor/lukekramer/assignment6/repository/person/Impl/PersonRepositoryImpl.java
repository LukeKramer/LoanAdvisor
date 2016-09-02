package com.example.lukekramer.assignment6.repository.person.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.assignment6.databases.DBConstants;
import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.repository.person.PersonRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 19/04/16.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepository {

    private static final String TAG_Person="Person TEST";
    public static final String TABLE_SETTINGS = "person";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "firstname";
    public static final String COLUMN_LNAME = "lastname";
    public static final String COLUMN_NUMBER = "phonenumber";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_INCOME = "income";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SETTINGS + " ("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FNAME + " TEXT NOT NULL , "
            + COLUMN_LNAME + " TEXT NOT NULL , "
            + COLUMN_NUMBER + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL , "
            + COLUMN_INCOME + " TEXT NOT NULL );";

    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS"+ TABLE_SETTINGS + ";";

    public PersonRepositoryImpl(Context context) {
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
        //db.execSQL(DATABASE_DROP);
        //db.execSQL(DATABASE_CREATE);
    }


    @Override
    public Person save(Person entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FNAME, entity.getFirstName());
        values.put(COLUMN_LNAME, entity.getLastName());
        values.put(COLUMN_NUMBER, entity.getPhoneNumber());
        values.put(COLUMN_EMAIL, entity.getEmailAddress());
        values.put(COLUMN_INCOME, entity.getIncome());
        long id = db.insertOrThrow(TABLE_SETTINGS, null, values);
        Log.i(TAG_Person,"HERE!!!!!!");
        //System.out.println("HERE\n\n\n\n" + id + "\n\n\n");
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .Id(new Long(id))
                .build();
        return insertedEntity;



    }

    @Override
    public Person delete(Person entity) {
        open();
        db.delete(
                TABLE_SETTINGS,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Person findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SETTINGS,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FNAME,
                        COLUMN_LNAME,
                        COLUMN_NUMBER,
                        COLUMN_EMAIL,
                        COLUMN_INCOME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Person person = new Person.Builder()
                    .Id(cursor.getLong(0))
                    .FirstName(cursor.getString(1))
                    .LastName(cursor.getString(2))
                    .PhoneNumber(cursor.getString(3))
                    .Email(cursor.getString(4))
                    .Income(cursor.getLong(5))
                    .build();

            return person;
        } else {
            return null;
        }
    }


    @Override
    public Person update(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FNAME, entity.getFirstName());
        values.put(COLUMN_LNAME, entity.getLastName());
        values.put(COLUMN_NUMBER, entity.getPhoneNumber());
        values.put(COLUMN_EMAIL, entity.getEmailAddress());
        values.put(COLUMN_INCOME, entity.getIncome());
        db.update(
                TABLE_SETTINGS,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }



    @Override
    public Set<Person> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SETTINGS;
        Set<Person> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Person person = new Person.Builder()
                        .Id(cursor.getLong(0))
                        .FirstName(cursor.getString(1))
                        .LastName(cursor.getString(2))
                        .PhoneNumber(cursor.getString(3))
                        .Email(cursor.getString(4))
                        .Income(cursor.getLong(5))
                        .build();
                settings.add(person);
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        onCreate(db);

    }





}
