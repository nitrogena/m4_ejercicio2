package mx.nitrogena.dadm.mod4.nim4ejercicio1;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.UserModel;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.service.ServiceTimer;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.util.PreferenceUtil;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsuario;
    private EditText etContra;
    private View vwLoading;
    private PreferenceUtil preferenceUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtener datos de sharePreference
        //si key_remember && key_id_user!=0
        //buscamos en BD
        /*Cursor c =db.query("USERTABLE",null,"_id=?",new String[]{"_idUser"},null,null,null,null);
        if(c.moveToNext())
        {
            //getDataFrom table User
            rerun ModelUser;
        }
        if(modelUser!=null)
        {
            mPassword.setText(modelUser.password);
            mUser.setText(modelUser.mUser);
        }*/

        //login
         /*Cursor c =db.query("USERTABLE",null,"username=? and pass=?",new String[]{"user","pass},null,null,null,null);*/




        setContentView(R.layout.activity_main);
        etUsuario = (EditText) findViewById(R.id.amain_et_usuario);
        etContra = (EditText) findViewById(R.id.amain_et_contrasenia);
        vwLoading = findViewById(R.id.amain_pb_progress);
        findViewById(R.id.amain_bt_ingresar).setOnClickListener(this);


        findViewById(R.id.btnRegisterLogin).setOnClickListener(this);
        preferenceUtil= new PreferenceUtil(getApplicationContext());
        CheckBox checkBox = (CheckBox) findViewById(R.id.chkRememberMe);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(ServiceTimer.TAG, "Checkeo es: "+isChecked);
            }
        });
        ((TextView)findViewById(R.id.txtDate))
                .setText(new SimpleDateFormat("dd-MMM-yy hh:mm").format(new Date()));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.amain_bt_ingresar:
                autenticar();
                break;
            case R.id.btnRegisterLogin:
                launchRegister();
                break;
        }
    }

    private void autenticar() {
        final String strUsuario = etUsuario.getText().toString();
        final String strContra = etContra.getText().toString();
        vwLoading.setVisibility(View.VISIBLE);

        //thread
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vwLoading.setVisibility(View.GONE);


                UserModel modelUser = preferenceUtil.getUser();
                if(modelUser == null) {
                    Toast.makeText(getApplicationContext(), "You need register", Toast.LENGTH_SHORT).show();

                    //if (strUsuario.equals("Nidia") && strContra.equals("dadm")) {
                }
                //else if (!TextUtils.isEmpty(strUsuario) && !TextUtils.isEmpty(strContra)){
                else if(strUsuario.equals(modelUser.userName) && strContra.equals(modelUser.password)){
                    //popup pequeño
                    //Toast.makeText(getApplicationContext(), "Buscando", Toast.LENGTH_SHORT).show();
                    //makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.amain_pb_usuarioEncontrado), LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), R.string.amain_pb_usuarioEncontrado, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("cveUsuario", strUsuario);
                    startActivity(intent);

                    startService(new Intent(getApplicationContext(), ServiceTimer.class));
                }
                else{
                    //Toast.makeText(getApplicationContext(), "Datos erroneos", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.amain_pb_usuarioNoEncontrado),Toast.LENGTH_SHORT).show();

                    //SUGERENCIA DE LA REVISION
                    Toast.makeText(getApplicationContext(), R.string.amain_pb_usuarioNoEncontrado, LENGTH_SHORT).show();
                }

            }
        },1000*1);
    }


    private void launchRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}


