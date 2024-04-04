package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.JsPromptResult;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Forgot extends AppCompatActivity implements JsonResponse {

    EditText e1;
    Button b1;

    String password,id;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        e1=(EditText) findViewById(R.id.recover);
        b1=(Button) findViewById(R.id.submit);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=e1.getText().toString();



                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Forgot.this;
                String q = "/accountrecovery?password=" + password;
                q = q.replace(" ", "%20");
                JR.execute(q);

            }
        });
    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            String status = jo.getString("status");

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                id = ja1.getJSONObject(0).getString("login_id");


                SharedPreferences.Editor e = sh.edit();
                e.putString("lid", id);

                e.commit();
                startActivity(new Intent(getApplicationContext(),Confirm_password.class));


            }


        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}