package mx.nitrogena.dadm.mod4.nim4ejercicio1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.UserModel;

/**
 * Created by Nidia on 06/07/2016.
 */
public class UserDataSource {
    private final SQLiteDatabase db;

    public UserDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }
    public void saveUser(UserModel modelUser)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_USER_USERNAME, modelUser.userName);
        contentValues.put(MySqliteHelper.COLUMN_USER_PASSWORD, modelUser.password);

        db.insert(MySqliteHelper.TABLE_NAME_USER, null, contentValues);

        /* nombre de la tabla "table",
        un segundo parámetro en caso de que necesitemos insertar valores nulos en la tabla "nullColumnHack" en este caso lo dejaremos pasar ya que no lo vamos a usar y por lo tanto lo ponemos a null
        y como tercer parámetro "values" nos pide un ContentValues.
         */


    }
    public void deleteUser(UserModel modelUser)
    {
        db.delete(MySqliteHelper.TABLE_NAME_USER, MySqliteHelper.COLUMN_ID_USER + "=?",
                new String[]{String.valueOf(modelUser.id)});

        /*delete(table, whereClause, whereArgs)*/

    }

    public List<UserModel> getAllUsers()
    {
        List<UserModel> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_USER, null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID_USER));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PASSWORD));

            UserModel  modelUser = new UserModel(userName, password);
            modelUser.id = id;
            modelUser.userName = userName;
            modelUser.password = password;

            modelUserList.add(modelUser);
        }

        return modelUserList;
    }

    public UserModel getUser(String userName, String password) {

        String[] valores_recuperar = {"_id", "user_name", "password"};
        Cursor c = db.query(MySqliteHelper.TABLE_NAME_USER, valores_recuperar, "user_name=? and password=?",
                new String[]{userName, password}, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }

        /*Cursor c =db.query("USERTABLE",null,"username=? and pass=?",new String[]{"user","pass},null,null,null,null);*/

        /*query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)*/

        UserModel modelUser = new UserModel(c.getString(1), c.getString(2));
        modelUser.id = c.getInt(0);
        modelUser.userName = c.getString(1);
        modelUser.password = c.getString(2);

        /*db.close();
        c.close();*/

        return modelUser;
    }
/*http://elbauldeandroid.blogspot.mx/2013/02/base-de-datos-sqlite.html*/
}

