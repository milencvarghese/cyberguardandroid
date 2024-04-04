package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Comment extends AppCompatActivity implements JsonResponse {
    ListView l1;
    ImageView i1;
    EditText e1;
    String comment,cc;
    String[] cmt,date,value,fname;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        l1=(ListView) findViewById(R.id.listview);
        i1=(ImageView) findViewById(R.id.imageView1);
        e1=(EditText) findViewById(R.id.editext);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Comment.this;
        String q = "/viewcomments?id="+Homepage.post_id;
        q = q.replace(" ", "%20");
        JR.execute(q);




        i1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                comment=e1.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Comment.this;
                String q = "/comment?loginid="+sh.getString("log_id","")+"&id="+Homepage.post_id+"&comment="+comment;
                q = q.replace(" ", "%20");
                JR.execute(q);



            }
        });
        JsonReq JR1 = new JsonReq();
        JR1.json_response = (JsonResponse) Comment.this;
        String q1 = "/warning?user_id="+sh.getString("log_id","");
        q1 = q1.replace(" ", "%20");
        JR1.execute(q1);
    }

    @Override
    public void response(JSONObject jo) {
        try{
            String method=jo.getString("method");
            if (method.equalsIgnoreCase("comment")) {
                String status=jo.getString("status");
                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    cmt = new String[ja1.length()];
                    date = new String[ja1.length()];
                    fname = new String[ja1.length()];


                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {


                        cmt[i] = ja1.getJSONObject(i).getString("comment");
                        date[i] = ja1.getJSONObject(i).getString("date");
                        fname[i] = ja1.getJSONObject(i).getString("f_name");


                        value[i] = "comment :" + cmt[i] + "\ndate" + date[i];

                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                    l1.setAdapter(ar);
                    Customcommentlist ar=new Customcommentlist(Comment.this,fname,cmt);
                    l1.setAdapter(ar);
                }
                else if (status.equalsIgnoreCase("bullying")) {
                    Toast.makeText(getApplicationContext(), "Bullying", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Homepage.class));

                }
                else if (status.equalsIgnoreCase("failed")) {
                    Toast.makeText(getApplicationContext(), "ACCOUNT BLOCKED DUE TO REPEAT BULLYING", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Log.class));

                }




                }
            if (method.equalsIgnoreCase("notcount")) {
                String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                    cc = ja1.getJSONObject(0).getString("cc");
                    if(cc.equalsIgnoreCase("3"))
                    {
                        Toast.makeText(getApplicationContext(), "ACCOUNT BLOCKED", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Log.class));
                    }
                }
            }





        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}