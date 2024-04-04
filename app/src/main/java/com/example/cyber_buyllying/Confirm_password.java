package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Confirm_password extends AppCompatActivity implements JsonResponse {


    EditText e1, e2;
    Button b1;

    String password,confirm;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        e1 = (EditText) findViewById(R.id.password);
        e2 = (EditText) findViewById(R.id.confirm);
        b1 = (Button) findViewById(R.id.submit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = e1.getText().toString();
                confirm = e2.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Confirm_password.this;
                String q = "/accountrecovery1?newpass=" + password + "&confirmpass=" + confirm+"&id="+sh.getString("lid","");
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

                Toast.makeText(getApplicationContext(), "Recovery Succesfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Log.class));


            }

        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}
