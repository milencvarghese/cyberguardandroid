package com.example.cyber_buyllying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class Homepage extends AppCompatActivity implements JsonResponse{

    ListView l1;
    ImageView i1,i2,i3,i4,i5;
    String[] fname,post,date,value,path,postid,userid,login_id;
    String notcount,count;
    public static String post_id,user_id,name,loginid;
    SharedPreferences sh;

    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        l1=(ListView) findViewById(R.id.list1);
        i5=findViewById(R.id.imageView1);
        i1=(ImageView) findViewById(R.id.imageView2);
        i2=(ImageView) findViewById(R.id.imageView4);
        i3=(ImageView) findViewById(R.id.imageView3);
        i4=(ImageView) findViewById(R.id.imageView5);
        t1=(TextView) findViewById(R.id.textView7);
        t2=(TextView) findViewById(R.id.textView14);

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Homepage.class));
            }
        });





        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Recentchat.class));
            }
        });


        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Complaint.class));
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Managepost.class));
            }
        });


        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Notification.class));
            }
        });

//        JsonReq J= new JsonReq();
//        J.json_response = (JsonResponse) Homepage.this;
//        String q1 = "/view_post";
//        q1 = q1.replace(" ", "%20");
//        J.execute(q1);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                post_id=postid[position];
                user_id=userid[position];
                name=fname[position];
                loginid=login_id[position];


                final CharSequence[] items = { "Chat","Comments", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
                builder.setTitle("Select!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (items[item].equals("Chat")) {
                            Intent il=new Intent(getApplicationContext(),Newchat.class);
                            startActivity(il);

                        }else if (items[item].equals("Comments")) {
                            Intent il=new Intent(getApplicationContext(),Comment.class);
                            startActivity(il);

                        }



                        else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });






        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Homepage.this;
        String q = "/view_post";
        q = q.replace(" ", "%20");
        JR.execute(q);


        JsonReq JR1 = new JsonReq();
        JR1.json_response = (JsonResponse) Homepage.this;
        String q1 = "/warning?user_id="+sh.getString("log_id","");
        q1 = q1.replace(" ", "%20");
        JR1.execute(q1);


        JsonReq JR12 = new JsonReq();
        JR12.json_response = (JsonResponse) Homepage.this;
        String q12 = "/count?id="+sh.getString("log_id","");
        q12 = q12.replace(" ", "%20");
        JR12.execute(q12);


    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");

            String status = jo.getString("status");
//            Log.d("pearl", status);
//            Toast.makeText(this, "logined", Toast.LENGTH_SHORT).show();
            if (method.equalsIgnoreCase("post")) {
            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                fname = new String[ja1.length()];
                post = new String[ja1.length()];
                date = new String[ja1.length()];
                path = new String[ja1.length()];
                postid = new String[ja1.length()];
                userid = new String[ja1.length()];
                login_id = new String[ja1.length()];


                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {


                    fname[i] = ja1.getJSONObject(i).getString("f_name");
                    post[i] = ja1.getJSONObject(i).getString("post");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    path[i] = ja1.getJSONObject(i).getString("path");
                    postid[i] = ja1.getJSONObject(i).getString("post_id");
                    userid[i] = ja1.getJSONObject(i).getString("user_id");
                    login_id[i] = ja1.getJSONObject(i).getString("login_id");


                    value[i] = "name :" + fname[i] + "\npost :" + post[i] + "\ndate" + date[i];

                }
            }
//                ArrayAdapter<String> ar=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,value);
//                l1.setAdapter(ar);
                Customimg ci = new Customimg(Homepage.this,path,fname,post);
                l1.setAdapter(ci);





            }
            if(method.equalsIgnoreCase("notcount")){
                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    notcount = ja1.getJSONObject(0).getString("cc");
                    if (ja1.getJSONObject(0).getString("cc").equals("1")) {
                        t1.setText("         first warning..! hey...user! make sure your comments good           ");
// Enable marquee scroll
                        t1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                        t1.setMarqueeRepeatLimit(-1); // -1 means infinite loop

// Make the TextView focusable to enable scrolling
                        t1.setFocusable(true);
                        t1.setFocusableInTouchMode(true);

// Set the scrolling behavior (you can customize it)
                        t1.setSingleLine(true); // Scrolls horizontally
                        t1.setHorizontallyScrolling(true);
                        t1.setSelected(true); // Start scrolling


                    } else if (ja1.getJSONObject(0).getString("cc").equals("2")) {
                        t1.setText("       second warning..! hey...user! make sure your comments good         ");
// Enable marquee scroll
                        t1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                        t1.setMarqueeRepeatLimit(-1); // -1 means infinite loop

// Make the TextView focusable to enable scrolling
                        t1.setFocusable(true);
                        t1.setFocusableInTouchMode(true);

// Set the scrolling behavior (you can customize it)
                        t1.setSingleLine(true); // Scrolls horizontally
                        t1.setHorizontallyScrolling(true);
                        t1.setSelected(true); // Start scrolling
                    }
                        else if (ja1.getJSONObject(0).getString("cc").equals("3")) {
                            t1.setText("       second warning..! hey...user! make sure your comments good         ");
// Enable marquee scroll
                            t1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                            t1.setMarqueeRepeatLimit(-1); // -1 means infinite loop

// Make the TextView focusable to enable scrolling
                            t1.setFocusable(true);
                            t1.setFocusableInTouchMode(true);

// Set the scrolling behavior (you can customize it)
                            t1.setSingleLine(true); // Scrolls horizontally
                            t1.setHorizontallyScrolling(true);
                            t1.setSelected(true); // Start scrolling


                    }else if (ja1.getJSONObject(0).getString("cc").equals("0")){
                        Toast.makeText(getApplicationContext(), "NO MESSAGES FOR YOU", Toast.LENGTH_LONG).show();


                    }
                    else if (ja1.getJSONObject(0).getString("cc").equals("3")){
                        startActivity(new Intent(getApplicationContext(),Log.class));


                    }




                }

                }
            if(method.equalsIgnoreCase("not")) {
                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    count = ja1.getJSONObject(0).getString("cc");
                    t2.setText(count);
                }
            }
        }
        catch(Exception e){
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void onBackPressed () {

        AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);

        builder.setTitle("Exit  :");
        builder.setMessage("Are you sure you want to exit..?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(),Log.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();

    }

}