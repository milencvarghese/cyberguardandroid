package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Complaint extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;
    String complaint;
    ListView l1;
    SharedPreferences sh;

    String[] complaints,reply,date,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        e1=(EditText) findViewById(R.id.complaint);
        b1=(Button) findViewById(R.id.send);
        l1=(ListView) findViewById(R.id.view);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint=e1.getText().toString();
                if (complaint.equalsIgnoreCase("")){
                    e1.setError("enter the complaint");
                    e1.setFocusable(true);
                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Complaint.this;
                    String q = "/comp?complaint=" + complaint + "&id=" + sh.getString("log_id", "");
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Complaint.this;
        String q = "/viewcom?id="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);



    }


    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            String status = jo.getString("status");

            if (method.equalsIgnoreCase("send_complaint")) {
                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "sent successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Complaint.class));

                } else {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }
            if (method.equalsIgnoreCase("view")) {

                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    complaints = new String[ja1.length()];
                    reply = new String[ja1.length()];
                    date = new String[ja1.length()];

                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {


                        complaints[i] = ja1.getJSONObject(i).getString("complaint");
                        reply[i] = ja1.getJSONObject(i).getString("reply");
                        date[i] = ja1.getJSONObject(i).getString("date");

                        value[i] = "complaint :" + complaints[i] + "\nreply:" + reply[i] + "\ndate" + date[i];

                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                    l1.setAdapter(ar);

                    Custom_complaint ar=new Custom_complaint(Complaint.this,complaints,reply,date);
                    l1.setAdapter(ar);

                }


            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}