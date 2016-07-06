package mx.nitrogena.dadm.mod4.nim4ejercicio1.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.R;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.adapter.ItemListAdapter;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.ItemModel;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.sql.ItemDataSource;

/**
 * Created by USUARIO on 18/06/2016.
 */
public class ListFragment extends Fragment {
    private ListView lvItems;

    //private List<ItemModel> lstItem = new ArrayList<>();

    private int intCuenta;
    private boolean blnBandera;

    private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource = new ItemDataSource(getActivity());
    }

    //para comunicar fragment con la actividad
    //OnListSelectedListener mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vwL = inflater.inflate(R.layout.fragment_list, container, false);
        lvItems = (ListView) vwL.findViewById(R.id.flist_lv_item);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View vwL, int position, long id) {
                ItemListAdapter adapter = (ItemListAdapter) parent.getAdapter();
                ItemModel modelItem = adapter.getItem(position);
                Toast.makeText(getActivity(), modelItem.item, Toast.LENGTH_SHORT).show();

                //Esto se uso primero con el arreglo, sin preferencias ni BD
                //ItemModel modelItem2 = lstItem.get(position);
                //Toast.makeText(getActivity(), modelItem2.item, Toast.LENGTH_SHORT).show();


                //para llamar a la actividad
                //mCallback.onListSelected(position);

                //yo lo puse
                //mCallback.onListSelected(modelItem2);
                //mCallback.onListSelected(position);


                //Otra forma de hacer la comunicacion
                //mostrarDetalles(modelItem2.item);



            }
        });


        List<ItemModel> modelItemList = itemDataSource.getAllItems();
        blnBandera = !(modelItemList.size()%2 == 0);
        intCuenta = modelItemList.size();
        lvItems.setAdapter(new ItemListAdapter(getActivity(), modelItemList));
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ItemListAdapter adapter = (ItemListAdapter) parent.getAdapter();
                final ItemModel modelItem = adapter.getItem(position);

                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.delete_title)
                        .setMessage(String.format("¿Desea borrar el elemento %s?", modelItem.item))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemDataSource.deleteItem(modelItem);
                                lvItems.setAdapter(new ItemListAdapter(getActivity(),
                                        itemDataSource.getAllItems()));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });



        final EditText etItem = (EditText) vwL.findViewById(R.id.flist_et_item);
        vwL.findViewById(R.id.flist_bt_registrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strItem = etItem.getText().toString();
                if (!TextUtils.isEmpty(strItem)) {
                    ItemModel item = new ItemModel();
                    item.item = strItem;
                    item.description = "Descripción " + intCuenta;
                    item.resourceId = blnBandera ? R.drawable.ic_action_extension : R.drawable.ic_notification_adb;

                    //Se quito, ya no usamos el arreglo,
                    //lstItem.add(item);

                    itemDataSource.saveItem(item);

                    //para el arreglo se usa el de abajo
                    //lvItems.setAdapter(new ItemListAdapter(getActivity(), lstItem));

                    //para base de datos
                    lvItems.setAdapter(new ItemListAdapter(getActivity(), itemDataSource.getAllItems()));
                    blnBandera = !blnBandera;
                    intCuenta++;
                    etItem.setText("");
                }

            }
        });
        return vwL;
    }

/*Mio, prueba 2 para comunicar fragment con activity
    void mostrarDetalles(String strItem){
        ProfileFragment details = (ProfileFragment) getFragmentManager().findFragmentById(R.id.adetail_frL_fragmentHolder);
        details = ProfileFragment.instanciar(strItem);

        getFragmentManager().beginTransaction().replace(R.id.adetail_frL_fragmentHolder, details).commit();


    }

*/

        // Container Activity must implement this interface
/*
    public interface OnListSelectedListener {
        public void onListSelected(int position);
        //public void onListSelected(ItemModel itemModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
        try {


            mCallback = (OnListSelectedListener) context;



        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnListSelectedListener");
        }
    }

    //SUGERENCIA DESPUES DE REVISION
    public void setmCallback(OnListSelectedListener mCallback)
    {
        this.mCallback = mCallback;
    }

    */





}
