package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Log extends AppCompatActivity implements JsonResponse{


    TextView t1,t2;
    EditText e1,e2;
    Button b1;
    String username,password;
    String loginid,usertype;

    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        t1=(TextView) findViewById(R.id.signup);

        e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.password);

        b1=(Button) findViewById(R.id.login);
        t2 = (TextView) findViewById(R.id.forgot);
        CheckBox showPasswordCheckbox = findViewById(R.id.show_password_checkbox);
        showPasswordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    e2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=e1.getText().toString();
                password=e2.getText().toString();
                if(username.equalsIgnoreCase("")){
                    e1.setError("Enter Username");
                    e1.setFocusable(true);
                }
                else if(password.equalsIgnoreCase("")){
                    e2.setError("Enter Password");
                    e2.setFocusable(true);
                }else{
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Log.this;
                    String q = "/logins?uname=" + username + "&pword=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }










            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Reg.class));
            }
        });




        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Forgot.class));
            }
        });


    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
//            Log.d("pearl", status);
//            Toast.makeText(this, "logined", Toast.LENGTH_SHORT).show();

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                loginid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("user_type");
//                Toast.makeText(getApplicationContext(), "Login Successfully"+loginid, Toast.LENGTH_LONG).show();


                SharedPreferences.Editor e = sh.edit();
                e.putString("log_id", loginid);

                e.commit();


                if (usertype.equals("user")) {
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Homepage.class));

                }
                else if(usertype.equals("blocked")){
                    Toast.makeText(getApplicationContext(), "Blocked Account", Toast.LENGTH_LONG).show();


                }
            }
            else{
                Toast.makeText(getApplicationContext(), "INCORRECT ENTRY", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), IpSetting.class);
        startActivity(b);

    }
}
