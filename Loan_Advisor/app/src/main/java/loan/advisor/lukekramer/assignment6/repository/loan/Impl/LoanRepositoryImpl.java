package com.example.lukekramer.assignment6.repository.loan.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.assignment6.databases.DBConstants;
import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.repository.loan.LoanRepository;

import java.util.HashSet;
import java.util.Set;



/**
 * Created by lukekramer on 21/04/16.
 */
public class LoanRepositoryImpl extends SQLiteOpenHelper implements LoanRepository {

    public static final String TABLE_SETTINGS = "loan";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MAXLOANAMOUNT = "max_loan_amount";
    public static final String COLUMN_MINLOANAMOUNT = "min_loan_amount";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SETTINGS + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MAXLOANAMOUNT + " TEXT NOT NULL , "
            + COLUMN_MINLOANAMOUNT+ " TEXT NOT NULL);";

    public LoanRepositoryImpl(Context context) {
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
    public Loan findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SETTINGS,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MAXLOANAMOUNT,
                        COLUMN_MINLOANAMOUNT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Loan loan = new Loan.Builder()
                    .ID(cursor.getLong(0))
                    .maxLoanAmount(cursor.getLong(1))
                    .minLoanAmount(cursor.getLong(2))
                    .build();

            return loan;
        } else {
            return null;
        }
    }

    @Override
    public Loan save(Loan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MAXLOANAMOUNT, entity.getMaxAmount());
        values.put(COLUMN_MINLOANAMOUNT, entity.getMinAmount());
        long id = db.insertOrThrow(TABLE_SETTINGS, null, values);
        Loan insertedEntity = new Loan.Builder()
                .copy(entity)
                .ID(new Long(id))
                .build();
        return insertedEntity;

    }

    @Override
    public Loan update(Loan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MAXLOANAMOUNT, entity.getMaxAmount());
        values.put(COLUMN_MINLOANAMOUNT, entity.getMinAmount());
        db.update(
                TABLE_SETTINGS,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Loan delete(Loan entity) {
        open();
        db.delete(
                TABLE_SETTINGS,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Loan> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_SETTINGS;
        Set<Loan> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Loan loan = new Loan.Builder()
                        .ID(cursor.getLong(0))
                        .maxLoanAmount(cursor.getLong(1))
                        .minLoanAmount(cursor.getLong(2))
                        .build();
                settings.add(loan);
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
