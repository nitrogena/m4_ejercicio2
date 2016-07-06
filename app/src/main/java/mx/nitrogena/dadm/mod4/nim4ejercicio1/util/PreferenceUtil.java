package mx.nitrogena.dadm.mod4.nim4ejercicio1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Date;

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

    public void saveDate(String strInicio){
        sp.edit().putString("ultimo_inicio", strInicio).apply();
    }

    public String getDate(){
        String strDate = sp.getString("ultimo_inicio", null);

        if (TextUtils.isEmpty(strDate)){
            return null;
        }
        return strDate;
    }
    public void saveDecision(boolean blnCheked){
        sp.edit().putBoolean("inicio_recordar", blnCheked).apply();
    }
    public boolean getDecision(){
        boolean blnDecision = sp.getBoolean("inicio_recordar", false);
        return  blnDecision;
    }

/*http://www.tutorialspoint.com/android/android_shared_preferences.htm*/

}
