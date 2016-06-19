package mx.nitrogena.dadm.mod4.nim4ejercicio1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.R;
import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.ItemModel;

/**
 * Created by USUARIO on 19/06/2016.
 */
public class ItemListAdapter extends ArrayAdapter<ItemModel> {
    public ItemListAdapter(Context context, List<ItemModel> objects) {
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        }
        TextView tvItemDesc = (TextView) convertView.findViewById(R.id.rwList_tv_ItemDesc);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.rwList_tv_titulo);
        ImageView imgRw = (ImageView) convertView.findViewById(R.id.rwList_img_registro);

        ItemModel itemModel = getItem(position);
        tvTitulo.setText(itemModel.item);
        tvItemDesc.setText(itemModel.id);
        imgRw.setImageResource(itemModel.resourceId);
        return convertView;
    }
}
