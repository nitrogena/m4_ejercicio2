package mx.nitrogena.dadm.mod4.nim4ejercicio1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.service.ServiceTimer;

/**
 * Created by Nidia on 05/07/2016.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "unamsqlite";
    private final static int DATABASE_VERSION = 3;

    public static final String TABLE_NAME = "item_table";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_DESC = "description";
    public static final String COLUMN_ITEM_RESOURCE = "resource_id";

    public static final String TABLE_NAME_USER = "user_table";
    public static final String COLUMN_ID_USER = BaseColumns._ID;
    public static final String COLUMN_USER_USERNAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "password";


    private static final String CREATE_USER_TABLE = "create table "+TABLE_NAME_USER+
            "("+COLUMN_ID_USER+" integer primary key autoincrement,"+
            COLUMN_USER_USERNAME+" text not null,"+
            COLUMN_USER_PASSWORD+ " text not null)";


    private static final String CREATE_TABLE = "create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_NAME+" text not null,"+
            COLUMN_ITEM_DESC+ " text not null,"+
            COLUMN_ITEM_RESOURCE+" integer not null)";




    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        //create user table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(ServiceTimer.TAG, "OnUpgrade SQL from "+oldVersion+ " to "+newVersion);

    }
}
