package com.example.hoanbk.todomvp.data.source.local;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hoanbk on 4/17/2017.
 */

public class TasksDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Tasks.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_EXTRIES =
            "CREATE TABLE " + TasksPersistanceContract.TaskEntry.TABLE_NAME + " (" +
                    TasksPersistanceContract.TaskEntry._ID + TEXT_TYPE + "PRIMARY KEY," +
                    TasksPersistanceContract.TaskEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    TasksPersistanceContract.TaskEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    TasksPersistanceContract.TaskEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    TasksPersistanceContract.TaskEntry.COLUMN_NAME_COMPLETED + BOOLEAN_TYPE +
                    " )";

    public TasksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_EXTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Not required as at version 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
