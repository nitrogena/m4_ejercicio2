package mx.nitrogena.dadm.mod4.nim4ejercicio1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.fragments.ListFragment;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.fragments.ProfileFragment;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.ItemModel;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.service.ServiceTimer;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.util.PreferenceUtil;

/**
 * Created by USUARIO on 18/06/2016.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    //se usa en el intent
    private String strUsuario;

    private PreferenceUtil preferenceUtil;

    private TextView txtTimer;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            txtTimer.setText(String.format("Session lenght %s seconds", counter));
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //comunicacion entre actividades
        //String strUsuario = getIntent().getExtras().getString("cveUsuario");
        strUsuario = getIntent().getExtras().getString("cveUsuario");
        String strBienvenida = String.format(getString(R.string.bienvenida), strUsuario);

        TextView tvMensajeB = (TextView) findViewById(R.id.adetail_tv_bienvenida);
        tvMensajeB.setText(strBienvenida);
        findViewById(R.id.adetail_bt_verPerfil).setOnClickListener(this);
        findViewById(R.id.adetail_bt_verLista).setOnClickListener(this);
        findViewById(R.id.adetail_bt_cerrar).setOnClickListener(this);

        //Para el servicio
        txtTimer = (TextView) findViewById(R.id.txtTimer);


        preferenceUtil = new PreferenceUtil(getApplicationContext());
        //con preferencias
        String strDate = preferenceUtil.getDate();
        TextView tvInicio = (TextView) findViewById(R.id.adetail_tv_ultimoInicio);
        tvInicio.setText(strDate);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adetail_bt_verPerfil:
                mostrarPerfil();
                break;
            case R.id.adetail_bt_verLista:
                mostrarLista();
                break;
            case R.id.adetail_bt_cerrar:
                cerrarSesion();
                break;
        }
    }

    //usando fragments
    private void mostrarPerfil() {
        //Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.amain_pb_usuarioNoEncontrado),Toast.LENGTH_SHORT).show();
        //ProfileFragment prFr = ProfileFragment.instanciar("Estás en la sección de perfil");
        ProfileFragment prFr = ProfileFragment.instanciar(strUsuario);
        getFragmentManager().beginTransaction().replace(R.id.adetail_frL_fragmentHolder, prFr).commit();
    }

    private void mostrarLista() {
        //Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.amain_pb_usuarioEncontrado),Toast.LENGTH_SHORT).show();
        ProfileFragment prFr = ProfileFragment.instanciar("lista");
        getFragmentManager().beginTransaction().replace(R.id.adetail_frL_fragmentHolder, new ListFragment()).commit();


        //SUGERENCIA DESPUES DE REVISION
       /* ListFragment listFragment = new ListFragment();
        listFragment.setmCallback((ListFragment.OnListSelectedListener) this);
        getFragmentManager().beginTransaction().replace(R.id.adetail_frL_fragmentHolder, listFragment).commit();*/

    }

    /*
    public void onListSelected(int position) {
        Toast.makeText(getApplicationContext(),"Llegando detali",Toast.LENGTH_SHORT).show();

        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        //ProfileFragment prFrDet = ProfileFragment.instanciar(itemModel.item);
        //getFragmentManager().beginTransaction().replace(R.id.adetail_frL_fragmentHolder, prFrDet).commit();


        //Toast.makeText(getApplicationContext(),"Ver datos",Toast.LENGTH_SHORT).show();

    }
    */

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver, filter);
        Log.d(ServiceTimer.TAG, "OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.TAG, "onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.TAG, "OnDestroy, terminando servicio");
        stopService(new Intent(getApplicationContext(), ServiceTimer.class));
    }

    private void cerrarSesion(){
        //txtTimer
    }
}