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

        db.insert(MySqliteHelper.TABLE_NAME_USER, null, contentValues);  /*REVISAR AQUI EL TABLE_NAME*/
    }
    public void deleteUser(UserModel modelUser)
    {
        db.delete(MySqliteHelper.TABLE_NAME_USER, MySqliteHelper.COLUMN_ID_USER + "=?",
                new String[]{String.valueOf(modelUser.id)});
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

}

