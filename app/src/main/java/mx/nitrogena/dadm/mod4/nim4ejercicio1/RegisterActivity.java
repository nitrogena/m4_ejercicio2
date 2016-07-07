package mx.nitrogena.dadm.mod4.nim4ejercicio1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.adapter.ItemListAdapter;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.ItemModel;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.UserModel;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.sql.UserDataSource;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.util.PreferenceUtil;

/**
 * Created by Nidia on 05/07/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    private UserDataSource userDataSource;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Para la base de datos
        userDataSource = new UserDataSource(getApplicationContext());

        final EditText mUser = (EditText) findViewById(R.id.mUserRegister);
        final EditText mPassword = (EditText) findViewById(R.id.mPasswordRegister);
        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = mUser.getText().toString();
                String password = mPassword.getText().toString();

                /* USAR PREFERENCIAS
                //salvar al disco

                PreferenceUtil util = new PreferenceUtil(getApplicationContext());
                util.saveUser(new UserModel(mUserName, password));
                finish();
                */


                if (!TextUtils.isEmpty(mUserName) && !TextUtils.isEmpty(password)) {
                    UserModel user = new UserModel(mUserName, password);
                    user.userName = mUserName;
                    user.password = password;

                    userDataSource.saveUser(user);



                    mUser.setText("");
                    mPassword.setText("");

                }
                finish();

            }
        });




    }
}
