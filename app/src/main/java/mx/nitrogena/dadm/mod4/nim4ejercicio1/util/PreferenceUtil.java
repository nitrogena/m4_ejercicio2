package mx.nitrogena.dadm.mod4.nim4ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.UserModel;


/**
 * Created by Nidia on 05/07/2016.
 */
public class PreferenceUtil {
    //En memoria
    private static final String FILE_NAME = "unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil(Context context)
    {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(UserModel modelUser)
    {
        //TODO validar si modelUser == null
        sp.edit().putString("user_name", modelUser.userName).apply();
        sp.edit().putString("user_password", modelUser.password).apply();
    }

    public UserModel getUser()
    {
        String mUser = sp.getString("user_name", null);
        String mPassword = sp.getString("user_password", null);
        if (TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword)){
            return null;
        }
        return new UserModel(mUser, mPassword);

    }


}
