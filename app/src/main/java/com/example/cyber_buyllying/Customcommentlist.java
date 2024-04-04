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

public class Customcommentlist extends ArrayAdapter<String> {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] user,comments,post;


    public Customcommentlist(Activity context,String[] fname,String[] comment) {
        super(context, R.layout.activity_customlist, fname);
        this.context = context;
//        this.file = file;
        this.comments=comment;
        this.user=fname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_customlist, null, true);
        //cust_list_view is xml file of layout created in step no.2

//        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView);
        TextView t=(TextView)listViewItem.findViewById(R.id.textView12);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);





        t.setText(" "+ comments[position]);
        t1.setText(" "+ user[position]);




        sh= PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+user[position];
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