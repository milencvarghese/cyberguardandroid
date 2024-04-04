//package com.example.cyber_buyllying;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import com.example.cyber_buyllying.JsonResponse;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Managepost extends AppCompatActivity implements JsonResponse {
//
//
//    EditText e1,e2,e3;
//
//    ImageButton img1;
//
//
//    String status;
//
//    Button b1,b2;
//
//    SharedPreferences sh;
//
//    public  static  String cy_post,image,FileUploadAsync;
//
//    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
//
//
//    public static String encodedImage = "", path = "";
//    byte[] byteArray = null;
//    private Uri mImageCaptureUri;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_managepost);
//        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//
//        e1=(EditText) findViewById(R.id.cy_post);
//        img1=(ImageButton) findViewById(R.id.image1);
//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {selectImageOption();}
//        });
//
//        b1=(Button) findViewById(R.id.button3);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                cy_post=e1.getText().toString();
//
//
//                SharedPreferences.Editor e = sh.edit();
//                e.putString("cy_post", cy_post);
//                e.commit();
//
//
//                Toast.makeText(getApplicationContext(),"PST : "+cy_post,Toast.LENGTH_LONG).show();
////
////                image=img1.getImageTintMode().toString();
////                JsonReq JR = new JsonReq();
////                JR.json_response = (JsonResponse) Managepost.this;
////                String q = "/managepost?post=" + cy_post +"&path="+image+ "&user_id=" + sh.getString("user_id", "");
////                q = q.replace(" ", "%20");
////                JR.execute(q);
//                sendAttach();
//            }
//        });
//
//
//
//    }
//    private void sendAttach() {
//
//        try {
//            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////	            String uid = sh.getString("uid", "");
//
//
//
//
////                String q = "http://" + IpSetting.text + "/managepost";
//            String q = "http://" +sh.getString("ip","")+"/managepost";
////            Toast.makeText(getApplicationContext(), " qq"+q, Toast.LENGTH_LONG).show();
//            Log.d("hhttpp",q);
////            Toast.makeText(getApplicationContext(), "byteArray"+byteArray, Toast.LENGTH_LONG).show();
//
//
//
//            Map<String, byte[]> aa = new HashMap<>();
//
//            aa.put("images", byteArray);
//            Toast.makeText(getApplicationContext(), "byteArray"+byteArray, Toast.LENGTH_LONG).show();
//
//            aa.put("logid",sh.getString("log_id","").getBytes());
//            aa.put("cy_post",sh.getString("cy_post","").getBytes());
////            aa.put("house",house.getBytes());
//
//            FileUploadAsync fua = new FileUploadAsync(q);
//            fua.json_response = (JsonResponse) Managepost.this;
//            fua.execute(aa);
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void response(JSONObject jo) {
//
//        try {
//            String status = jo.getString("status");
//            Log.d("pearl", status);
//
//            if (status.equalsIgnoreCase("success")) {
//
//                Toast.makeText(getApplicationContext(), "update Succesfull", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(),Homepage.class));
//
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//
//
//    private void selectImageOption() {
//        /*Android 10+ gallery code
//        android:requestLegacyExternalStorage="true"*/
//
//        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(Managepost.this);
//        builder.setTitle("Take Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//
//                if (items[item].equals("Capture Photo")) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//
//                    startActivityForResult(intent, CAMERA_PIC_REQUEST);
//
//                } else if (items[item].equals("Choose from Gallery")) {
//                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, GALLERY_CODE);
//
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {
//
//            mImageCaptureUri = data.getData();
//            System.out.println("Gallery Image URI : " + mImageCaptureUri);
//            //   CropingIMG();
//
//            Uri uri = data.getData();
//            Log.d("File Uri", "File Uri: " + uri.toString());
//            // Get the path
//            //String path = null;
//            try {
////                path = FileUtils.getPath(this, uri);
////                path= FileUtils.getPath(this,uri);
//                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();
//
//                File fl = new File(path);
//                int ln = (int) fl.length();
//
//                InputStream inputStream = new FileInputStream(fl);
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                byte[] b = new byte[ln];
//                int bytesRead = 0;
//
//                while ((bytesRead = inputStream.read(b)) != -1) {
//                    bos.write(b, 0, bytesRead);
//                }
//                inputStream.close();
//                byteArray = bos.toByteArray();
//
//                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                img1.setImageBitmap(bit);
//
//                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
//                encodedImage = str;
////                sendAttach1();w
//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//            }
//        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
//
//            try {
//                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                img1.setImageBitmap(thumbnail);
//                byteArray = baos.toByteArray();
//
//                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
//                encodedImage = str;
////                sendAttach1();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//    public void onBackPressed()
//    {
//        // TODO Auto-generated method stub
//        super.onBackPressed();
//        Intent b=new Intent(getApplicationContext(),Homepage.class);
//        startActivity(b);
//    }
//
//}




package com.example.cyber_buyllying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cyber_buyllying.JsonResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;



public class Managepost extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3;

    ImageView img1;




    Button b1,b2;
    public  static  String cy_post,image,FileUploadAsync;

    SharedPreferences sh;
    private final int REQUEST_TAKE_GALLERY_VIDEO = 305;
    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    private static final int CAMERA_VIDEO_REQUEST = 101; // Define your request code here
    private static final int GALLERY_VIDEO_REQUEST = 102; // Define your request code here
    public static String encodedImage = "", path = "",title,ft,type,newval="",amnt;
    private Uri mImageCaptureUri,selectedVideoUri;
    private  Uri videoUri;
    byte[] byteArray = null;
    byte[] byteArray2 = null;

    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_managepost);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.cy_post);
        img1=(ImageView) findViewById(R.id.image1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectImageOption();}
        });


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageOption();
            }
        });

        b1=(Button) findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cy_post=e1.getText().toString();


                SharedPreferences.Editor e = sh.edit();
                e.putString("cy_post", cy_post);
                e.commit();


                Toast.makeText(getApplicationContext(),"PST : "+cy_post,Toast.LENGTH_LONG).show();
//
//                image=img1.getImageTintMode().toString();
//                JsonReq JR = new JsonReq();
//                JR.json_response = (JsonResponse) Managepost.this;
//                String q = "/managepost?post=" + cy_post +"&path="+image+ "&user_id=" + sh.getString("user_id", "");
//                q = q.replace(" ", "%20");
//                JR.execute(q);
                sendAttach();
            }
        });
    }

    private void sendAttach() {

        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//              String uid = sh.getString("uid", "");
//               String q = "http://" + IpSetting.ip + "/smart city/apis.php";
            String q = "http://" +sh.getString("ip","")+"/managepost";

//            Toast.makeText(getApplicationContext(), "Byte Array:" + byteArray.length, Toast.LENGTH_LONG).show();
            Map<String, byte[]> aa = new HashMap<>();

            aa.put("image", byteArray);
            Toast.makeText(getApplicationContext(), "byteArray"+byteArray, Toast.LENGTH_LONG).show();

            aa.put("logid",sh.getString("log_id","").getBytes());
            aa.put("cy_post",sh.getString("cy_post","").getBytes());
//            aa.put("house",house.getBytes());

            FileUploadAsync fua = new FileUploadAsync(q);
            fua.json_response = (JsonResponse) Managepost.this;
            fua.execute(aa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }
    private void selectImageOption() {




        final CharSequence[] items = { "Capture Photo","Choose from Gallery","Gallery Video", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Managepost.this);
        builder.setTitle("Select!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                }
                else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);
                    }

                else if (items[item].equals("Gallery Video")) {


                    newval="video";
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);

                }
                else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK &&null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
//   CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
// Get the path
            //String path = null;
            try {
                path = FileUtils.getPath(this, uri);
                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();

                File fl = new File(path);
                int ln = (int) fl.length();

                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray = bos.toByteArray();

                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img1.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                img1.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
                Toast.makeText(getApplicationContext(),"image : "+ encodedImage, Toast.LENGTH_LONG).show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == CAMERA_VIDEO_REQUEST && resultCode == RESULT_OK) {


            Uri videoUri = data.getData();


        }
        else if (requestCode == GALLERY_VIDEO_REQUEST && resultCode == RESULT_OK) {

            Uri videoUri = data.getData();



        }

    }


    @Override
    public void response(JSONObject jo) {

        try {
            String status = jo.getString("status");
            Log.d("result", status);

            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "Result Found....\n Successfully Updated", Toast.LENGTH_LONG).show();
//                     JSONArray ja = (JSONArray) jo.getJSONArray("data");
//                     labels = ja.getJSONObject(0).getString("label");
//                     pre = ja.getJSONObject(0).getString("precatuions");



                //startActivity(new Intent(getApplicationContext(),Viewmodeldetails.class));
                startActivity(new Intent(getApplicationContext(), Homepage.class));

            }
            else if (status.equalsIgnoreCase("failed")) {

                Toast.makeText(getApplicationContext(),"Image Not Found", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Managepost.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Response Exc : " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }



    public void onBackPressed () {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Homepage.class));
    }





//////////////////////////////////////////////////////
}



