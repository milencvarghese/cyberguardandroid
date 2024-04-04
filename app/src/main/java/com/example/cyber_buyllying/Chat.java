package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Chat extends AppCompatActivity implements JsonResponse{



    EditText e1;

    Button b1;

    String chat;


    String[] chat1,value,user,sid,date;

    SharedPreferences sh,sh1;

    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView) findViewById(R.id.list);


        e1=(EditText) findViewById(R.id.chat);
        b1=(Button) findViewById(R.id.chatsend);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat=e1.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Chat.this;
                String q = "/chat?chat=" + chat +  "&user_id=" + sh.getString("log_id", "")+"&id=" + Homepage.loginid;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Chat.this;
        String q = "/viewchat?user_id=" + sh.getString("log_id", "")+"&id=" + Homepage.loginid;
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo)  {


        try {
            String method = jo.getString("method");
            if (method.equalsIgnoreCase("done")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {

                    Toast.makeText(getApplicationContext(), "chat send Succesfull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Chat.class));




                }
            }
            if (method.equalsIgnoreCase("view")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {


                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                    user = new String[ja1.length()];
                    sid = new String[ja1.length()];

                    user = new String[ja1.length()];


                    date = new String[ja1.length()];


                    value = new String[ja1.length()];



                    for (int i = 0; i < ja1.length(); i++) {
                        chat1[i] = ja1.getJSONObject(i).getString("message");
                        sid[i] = ja1.getJSONObject(i).getString("sender_id");
                        date[i] = ja1.getJSONObject(i).getString("time");




                        value[i] = "chat : " + chat1[i];
                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                    l1.setAdapter(ar);
                    Customchat ar=new Customchat(Chat.this,chat1,sid,date);
                    l1.setAdapter(ar);

//                Toast.makeText(getApplicationContext(), "commented Successfull", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), UserHomenew.class));

                }

            }
        }
        catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


    }
    public void onBackPressed () {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Homepage.class));
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
}