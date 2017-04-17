package com.example.hoanbk.todomvp.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by hoanbk on 4/17/2017.
 * The contract used for the db to save the tasks locally
 */

public final class TasksPersistanceContract {

    // To prevent someone from accidentally instantiating the contract class
    // give it an empty constructor.

    /* Inner class that defines the table contents*/
    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }
}
