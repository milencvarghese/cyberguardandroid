package com.example.cyber_buyllying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recentchat extends AppCompatActivity implements JsonResponse{


    ListView l1;

    ImageView img1;


    SharedPreferences sh;

    String[] fname,lname,value,user_id,login_id;

    public static String userid,f_name,loginid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentchat);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Assuming you have an ImageView with ID R.id.imageView3 in your XML layout

//        ImageView im1 = findViewById(R.id.imageView11);
//
//// Load GIF from resources using Glide
//        int gifResource = R.drawable.chatgg; // Replace with the resource ID of your GIF
//        Glide.with(this).asGif().load(gifResource).into(im1);

// Set GIF from resources directly (without Glide)
// Comment out or remove the Glide code above if you only want to use setImageResource
// int gifResource = R.drawable.bellrun2; // Already declared above
//        im1.setImageResource(gifResource);


        l1=(ListView) findViewById(R.id.list);
        img1=(ImageView) findViewById(R.id.dot);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Recentchat.this); // Use the correct context here
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to Exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "Yes" button click event
                        // Put your code here for the action to be taken on "Yes"
                        startActivity(new Intent(getApplicationContext(),Homepage.class));

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "No" button click event
                        // Put your code here for the action to be taken on "No"
                        dialog.dismiss(); // Close the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Recentchat.this;
        String q = "/recentchat?id=" + sh.getString("log_id", "");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();

                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                fname = new String[ja1.length()];
                lname = new String[ja1.length()];
                user_id = new String[ja1.length()];
                login_id = new String[ja1.length()];



                value = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    fname[i] = ja1.getJSONObject(i).getString("f_name");
                    lname[i] = ja1.getJSONObject(i).getString("l_name");
                    user_id[i] = ja1.getJSONObject(i).getString("user_id");
                    login_id[i] = ja1.getJSONObject(i).getString("login_id");


                    value[i] = "f_name : " + fname[i] + "\nl_name : " + lname[i];




//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);

//
                Custom_recent ci = new Custom_recent(Recentchat.this,fname);

                l1.setAdapter(ci);


                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Handle item click here
                        userid=user_id[position];
                        f_name=fname[position];
                        loginid=login_id[position];






                        Intent intent = new Intent(Recentchat.this, Recentchatnew.class);
                        startActivity(intent);
                    }
                });

            }

            else {
                Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

            }
        }

//            if(method.equalsIgnoreCase("user_send_work_request"))
//            {
//                String status=jo.getString("status");
//                Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
//                if(status.equalsIgnoreCase("success"))
//                {
//                    Toast.makeText(getApplicationContext(),"Requset Send Successfully!", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Sending Failed", Toast.LENGTH_LONG).show();
//                }
//            }

        catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Homepage.class);
        startActivity(b);
    }
}