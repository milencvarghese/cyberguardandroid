
package com.example.cyber_buyllying;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Customchat extends ArrayAdapter<String>
{
    //needs to extend ArrayAdapter

    private String[] chat1;         //for custom view name item
    private String[] date;
    private String[] sid;	//for custom view photo items
    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    public Customchat(Activity context, String[] chat1, String[] sid, String[] date)
    {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.activity_customchat, chat1);
        this.context = context;
        this.chat1 = chat1;
        this.date= date;
        this.sid= sid;
    }

    @SuppressLint("ResourceAsColor") @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //override getView() method
        sh = PreferenceManager.getDefaultSharedPreferences(getContext());

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_customchat, null, true);
        //cust_list_view is xml file of layout created in step no.2

        TextView t1= (TextView) listViewItem.findViewById(R.id.textView1);
        TextView t2 = (TextView) listViewItem.findViewById(R.id.textView2);

        if(sid[position].equalsIgnoreCase(sh.getString("log_id", "")))
        {
            t2.setText(""+chat1[position]+""+"\n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t "+date[position]+"");
//            t2.setText(message[position]);
//			t2.setBackgroundColor(Color.parseColor("#82ccdd"));
            t2.setBackgroundResource(R.drawable.send_mssg);



        }
        else
        {
            t1.setText(""+chat1[position]+""+"\n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t "+date[position]+"");
//            t1.setText(message[position]);
//			t1.setBackgroundColor(Color.parseColor("#78e08f"));
            t1.setBackgroundResource(R.drawable.received);
        }
        sh=PreferenceManager.getDefaultSharedPreferences(getContext());
        return  listViewItem;
    }
}
