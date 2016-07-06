package mx.nitrogena.dadm.mod4.nim4ejercicio1.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;
import java.util.List;

import mx.nitrogena.dadm.mod4.nim4ejercicio1.model.ItemModel;

/**
 * Created by Nidia on 05/07/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    public ItemDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }
    public void saveItem(ItemModel modelItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME, modelItem.item);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC, modelItem.description);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE, modelItem.resourceId);
        db.insert(MySqliteHelper.TABLE_NAME, null, contentValues);
    }
    public void deleteItem(ItemModel modelItem)
    {
        db.delete(MySqliteHelper.TABLE_NAME, MySqliteHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(modelItem.id)});
    }

    public List<ItemModel> getAllItems()
    {
        List<ItemModel> modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            String desccription = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            int resourceId = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            ItemModel  modelItem = new ItemModel();
            modelItem.id = id;
            modelItem.resourceId = resourceId;
            modelItem.description = desccription;
            modelItem.item = itemName;
            modelItemList.add(modelItem);
        }

        return modelItemList;
    }

}
