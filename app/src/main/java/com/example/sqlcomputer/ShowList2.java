package com.example.sqlcomputer;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ShowList2 extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list2);
        updateUI();
        Button btn=(Button) findViewById(R.id.buttonBack);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ShowList2.this.finish();
            }
        });
    }
    List<InforData>list=new ArrayList<InforData>();
    public void updateUI()
    {
        SQLiteDatabase database=openOrCreateDatabase("mydata.db", Context.MODE_PRIVATE, null);
        if(database!=null)
        {
            Cursor cursor=database.query("tblAuthors", null, null, null, null, null, null);
            startManagingCursor(cursor);
            InforData header=new InforData();
            header.setField1(cursor.getColumnName(0));
            header.setField2(cursor.getColumnName(1));
            header.setField3(cursor.getColumnName(2));
            list.add(header);
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                InforData data=new InforData();
                data.setField1(cursor.getInt(0));
                data.setField2(cursor.getString(1));
                data.setField3(cursor.getString(2));
                list.add(data);
                cursor.moveToNext();
            }
            cursor.close();
            ComputerAdapter adapter=new ComputerAdapter(ShowList2.this, R.layout.mylayout_show_listdata, list);
            setListAdapter(adapter);
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Toast.makeText(ShowList2.this,"View->"+ list.get(position).toString(), Toast.LENGTH_LONG).show();
    }

}
