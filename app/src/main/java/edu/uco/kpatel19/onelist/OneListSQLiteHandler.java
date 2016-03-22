package edu.uco.kpatel19.onelist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cary on 3/22/2016.
 */
public class OneListSQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User_Info.db";
    private static final String TABLE_NAME = "User_Info";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "_username";

    public OneListSQLiteHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
