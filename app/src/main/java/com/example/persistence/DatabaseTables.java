package com.example.persistence;

class DatabaseTables {

    static class appdata {

        static final String TABLE_NAME = "appdata";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_TEXT = "text";

    }

    static final String SQL_CREATE_TABLE_APPDATA =
            // "CREATE TABLE mountain (id INTEGER PRIMARY KEY, name TEXT, height INT)"
            "CREATE TABLE " + appdata.TABLE_NAME + " (" +
                    appdata.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    appdata.COLUMN_NAME_TEXT + " TEXT)";

    static final String SQL_DELETE_TABLE_APPDATA =
            // "DROP TABLE IF EXISTS mountain"
            "DROP TABLE IF EXISTS " + appdata.TABLE_NAME;

}
