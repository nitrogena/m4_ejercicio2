package mx.nitrogena.dadm.mod4.nim4ejercicio1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.R;



/**
 * Created by USUARIO on 18/06/2016.
 */
public class ProfileFragment extends Fragment{
    private ImageView imgPerfil;
    private boolean blnBandera;

    public static ProfileFragment instanciar(String strMensaje){
        ProfileFragment perFrag = new ProfileFragment();
        Bundle bndl = new Bundle();
        bndl.putString("cveMens", strMensaje);
        perFrag.setArguments(bndl);
        return perFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vwF = inflater.inflate(R.layout.fragment_profile, container, false);
        imgPerfil = (ImageView) vwF.findViewById(R.id.fprofile_img_perfil);
        TextView tvMensaje = (TextView) vwF.findViewById(R.id.txtUserFragment);
        Bundle bndl2 = getArguments();
        String strCveMens ;
        if (bndl2 != null)
            strCveMens = bndl2.getString("cveMens");
        else
            strCveMens = "Sin mensaje";

        tvMensaje.setText(strCveMens);
        return vwF;
    }



}
