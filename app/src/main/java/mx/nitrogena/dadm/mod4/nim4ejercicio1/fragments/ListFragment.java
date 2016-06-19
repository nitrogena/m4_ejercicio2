package mx.nitrogena.dadm.mod4.nim4ejercicio1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by USUARIO on 18/06/2016.
 */
public class ListFragment extends Fragment {
    private ListView lvItems;
    private List<ItemModel> lstItem = new ArrayList<>();
    private int intCuenta;
    private boolean blnBandera;

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
                ItemModel modelItem2 = lstItem.get(position);
                Toast.makeText(getActivity(), modelItem2.item, Toast.LENGTH_SHORT).show();
            }
        });


        final EditText etItem = (EditText) vwL.findViewById(R.id.flist_et_item);
        vwL.findViewById(R.id.flist_bt_registrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strItem = etItem.getText().toString();
                if(!TextUtils.isEmpty(strItem))
                {
                    ItemModel item = new ItemModel();
                    item.item = strItem;
                    item.id  = "Descripción "+intCuenta;
                    item.resourceId = blnBandera ? R.drawable.ic_action_extension : R.drawable.ic_notification_adb;
                    lstItem.add(item);
                    lvItems.setAdapter(new ItemListAdapter(getActivity(), lstItem));
                    blnBandera = !blnBandera;
                    intCuenta++;
                    etItem.setText("");
                }

            }
        });
        return vwL;
    }
}
