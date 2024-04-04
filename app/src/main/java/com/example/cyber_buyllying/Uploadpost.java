package com.example.cyber_buyllying;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONObject;


public class Uploadpost extends AppCompatActivity implements JsonResponse{
    ImageView m1;
    EditText e1;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpost);

        m1=(ImageView) findViewById(R.id.imageView1);
        e1=(EditText) findViewById(R.id.caption);
        b1=(Button) findViewById(R.id.POST);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public void response(JSONObject jo) {

    }
}