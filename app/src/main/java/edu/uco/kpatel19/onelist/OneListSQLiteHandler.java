package edu.uco.kpatel19.onelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String querry = "CREATE TABLE" + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_USERNAME + " TEXT " + ");";

        db.execSQL(querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean sqlliteExists () {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+TABLE_NAME+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    //add new item
    public void addUser(UserInfo user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_username());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    //delete item
    public void deleteUser(String username)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\";"  );

    }

    //print db
    public String dbToString(){

        String dataString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast());
        {
            if(cursor.getString(cursor.getColumnIndex("_username"))!= null);
            dataString += cursor.getString(cursor.getColumnIndex("_username"));
            dataString += "\n";
        }
        db.close();
        return dataString;
    }


}
