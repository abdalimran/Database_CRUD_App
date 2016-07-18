package com.github.abdalimran.contactdb.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME="contact_manager";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME ="contact_info";


    static final String COL_ID="id";
    static final String COL_NAME="name";
    static final String COL_PHONE="phone";

    static final String CREATE_TABLE="create table "+ TABLE_NAME +"( "+COL_ID+" integer primary key, "+
            COL_NAME+" text, "+COL_PHONE+" text);";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist "+ TABLE_NAME);
        onCreate(db);
    }
}
