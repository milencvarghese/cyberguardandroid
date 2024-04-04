package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Notification extends AppCompatActivity implements JsonResponse {


    ListView l1;
    SharedPreferences sh;
    String[] notification,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        l1=(ListView) findViewById(R.id.view);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Notification.this;
        String q = "/view?id="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);



        }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            if (method.equalsIgnoreCase("view")) {
                String status=jo.getString("status");
                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    notification = new String[ja1.length()];
//                    reply = new String[ja1.length()];
//                    date = new String[ja1.length()];

                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {


                        notification[i] = ja1.getJSONObject(i).getString("notification");
//                        reply[i] = ja1.getJSONObject(i).getString("reply");
//                        date[i] = ja1.getJSONObject(i).getString("date");

                        value[i] = "Notification  :" + notification[i] ;

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                    l1.setAdapter(ar);
                }
            }

            }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }






    }
}

