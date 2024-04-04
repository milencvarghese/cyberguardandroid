package com.example.cyber_buyllying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_complaint extends ArrayAdapter<String> {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] complaints,reply,date;


    public Custom_complaint(Activity context,String[] complaints,String[] reply,String[] date) {
        super(context, R.layout.activity_custom_complaint, complaints);
        this.context = context;
        this.complaints = complaints;
        this.reply=reply;
        this.date=date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_custom_complaint, null, true);
        //cust_list_view is xml file of layout created in step no.2

//        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView);
        TextView t=(TextView)listViewItem.findViewById(R.id.textView8);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView11);
        TextView t2=(TextView)listViewItem.findViewById(R.id.textView13);






        t.setText("Complaint : "+ complaints[position]);
        t1.setText("Reply : "+ reply[position]);
        t2.setText("Date : "+ date[position]);





        sh= PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+complaints[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
//        Picasso.with(context)
//                .load(pth)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background).into(im);

        return  listViewItem;
    }





    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}