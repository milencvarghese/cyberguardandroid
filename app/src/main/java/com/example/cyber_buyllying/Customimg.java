package com.example.cyber_buyllying;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class Customimg extends ArrayAdapter<String> implements JsonResponse {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] path,file,post,fname;
    public static String aa;
    ImageView i1,i2;

    public Customimg(Activity context,String[] file, String[] fname,String[] post) {
        //constructor of this class to get the values from main_activity_class





        super(context, R.layout.activity_customimg, file);

        this.context = context;
        this.file = file;
        this.fname=fname;
        this.post=post;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_customimg, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView);
        TextView t=(TextView)listViewItem.findViewById(R.id.textView6);
        TextView t1=(TextView)listViewItem.findViewById(R.id.postdetails);
//        ImageView im2 = (ImageView) listViewItem.findViewById(R.id.imageView5);
//        ImageView im3 = (ImageView) listViewItem.findViewById(R.id.imageView11);


//        im2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in=new Intent(context.getApplicationContext(),Comment.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(in);
//            }
//        });
//
//
//
//
//        im3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent in=new Intent(context.getApplicationContext(),Newchat.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(in);
//            }
//        });







        t.setText( fname[position]);
        t1.setText(post[position]);



        sh=PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+file[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(context)
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(im);

        return  listViewItem;
    }

    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void response(JSONObject jo) {

    }
}