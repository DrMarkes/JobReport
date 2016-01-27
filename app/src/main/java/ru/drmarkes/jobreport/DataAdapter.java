package ru.drmarkes.jobreport;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.drmarkes.jobreport.provider.ContractClass;

/**
 * Created by Андрей on 24.01.2016.
 */
public class DataAdapter extends CursorAdapter {
    private LayoutInflater layoutInflater;

    public DataAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View root = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        Holder holder = new Holder();
        TextView textViewRecord = (TextView)root.findViewById(android.R.id.text1);
        holder.textViewRecord = textViewRecord;
        root.setTag(holder);
        return root;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(ContractClass.Job._ID));
        long date = cursor.getLong(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_DATE));
        String order = cursor.getString(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_ORDER));
        String department = cursor.getString(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_DEPARTMENT));
        String manipulation = cursor.getString(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_MANIPULATION));
        String patient = cursor.getString(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_PATIENT));
        Integer roomHisrory = cursor.getInt(cursor.getColumnIndex(ContractClass.Job.COLUMN_NAME_ROOM_HISTORY));
        Holder holder = (Holder)view.getTag();
        if(holder != null) {
            holder.textViewRecord.setText(date + " | " + order + " | " + department +
                    " | " + manipulation + " | " + patient + " | " + roomHisrory);
            holder.RecordID = id;
        }
    }

    private static class Holder {
        public TextView textViewRecord;
        public long RecordID;
    }
}
