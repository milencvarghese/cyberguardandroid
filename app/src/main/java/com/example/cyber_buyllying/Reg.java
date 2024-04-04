package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;


public class Reg extends AppCompatActivity implements JsonResponse {


    EditText e1, e2, e3, e4, e5, e6, e7;

    Button b1;

    String fname, lname, place, phone, email, username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        e1 = (EditText) findViewById(R.id.f_name);
        e2 = (EditText) findViewById(R.id.l_name);
        e3 = (EditText) findViewById(R.id.place);
        e4 = (EditText) findViewById(R.id.phone);
        e5 = (EditText) findViewById(R.id.e_mail);
        e6 = (EditText) findViewById(R.id.username);
        e7 = (EditText) findViewById(R.id.password);

        b1 = (Button) findViewById(R.id.register);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = e1.getText().toString();
                lname = e2.getText().toString();
                place = e3.getText().toString();
                phone = e4.getText().toString();
                email = e5.getText().toString();
                username = e6.getText().toString();
                password = e7.getText().toString();

                if(fname.equalsIgnoreCase("")){
                    e1.setError("invalid field");
                    e1.setFocusable(true);
                }else if(lname.equalsIgnoreCase("")) {
                    e2.setError("invalid field");
                    e2.setFocusable(true);
                }else if(place.equalsIgnoreCase("")){
                    e3.setError("invalid field");
                    e3.setFocusable(true);
                }else if(phone.equalsIgnoreCase("")){
                    e4.setError("invalid field");
                    e4.setFocusable(true);
                }else if(email.equalsIgnoreCase("")){
                    e5.setError("invalid field");
                    e5.setFocusable(true);
                }else if(username.equalsIgnoreCase("")){
                    e6.setError("invalid field");
                    e6.setFocusable(true);
                }else if(password.equalsIgnoreCase("")){
                    e7.setError("invalid field");
                    e7.setFocusable(true);}
                else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Reg.this;
                    String q = "/reg?fname=" + fname + "&lname=" + lname + "&place=" + place + "&phone=" + phone + "&email=" + email + "&username=" + username + "&password=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            String status = jo.getString("status");

            if (status.equalsIgnoreCase("success")) {

                Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Log.class));


            }

        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }


}