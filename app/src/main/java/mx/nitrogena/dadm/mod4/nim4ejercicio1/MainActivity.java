package mx.nitrogena.dadm.mod4.nim4ejercicio1;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsuario;
    private EditText etContra;
    private View vwLoading;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario = (EditText) findViewById(R.id.amain_et_usuario);
        etContra = (EditText) findViewById(R.id.amain_et_contrasenia);
        vwLoading = findViewById(R.id.amain_pb_progress);
        findViewById(R.id.amain_bt_ingresar).setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.amain_bt_ingresar:
                autenticar();
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
                if (strUsuario.equals("Nidia") && strContra.equals("dadm")) {
                    //popup pequeño
                    Toast.makeText(getApplicationContext(), "Buscando", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.amain_pb_usuarioEncontrado),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Datos erroneos", Toast.LENGTH_SHORT).show();
                }

            }
        },1000*2);



    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mx.nitrogena.dadm.mod4.nim4ejercicio1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://mx.nitrogena.dadm.mod4.nim4ejercicio1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


