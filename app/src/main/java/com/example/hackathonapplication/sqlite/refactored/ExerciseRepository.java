package com.example.hackathonapplication.sqlite.refactored;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hackathonapplication.model.entity.Customer;

public class ExerciseRepository {
    Context context;
    SqliteTableScheme.ExerciseScheme scheme;
    SQLiteDatabase database;

    public ExerciseRepository(Context context) {
        this.context = context;
        this.scheme = new SqliteTableScheme.ExerciseScheme();
    }

    public void connect() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context, scheme);
        SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
        databaseHelper.onCreate(writableDatabase);
        this.database = writableDatabase;
    }

    public void close() {
        this.database.close();
    }

    public long insert(String prescription, String videopath, String contents) {
        ContentValues values = new ContentValues();

        values.put(scheme.PRESCRIPTION, prescription);
        values.put(scheme.VIDEOPATH, videopath);
        values.put(scheme.CONTENTS, contents);

        return database.insert(scheme.TABLE_NAME, null, values);
    }

    public Customer findByPrescription(String prescription) {
        Cursor c = database.rawQuery(
                " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.PRESCRIPTION + " = " + prescription,
                null);

        Customer result = null;
        while (c.moveToNext()) {
            // todo conver model here
        }

        c.close();

        return result;
    }

}