package com.stasbizdiga.pamlab5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Map;


public class Sign_Up extends Activity {

    Bitmap bitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override  // Photo-Take Handling
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            ImageView pic = (ImageView) findViewById(R.id.imageView3);
            pic.setImageBitmap(bitmap);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //assigning default pic
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.addphoto2);

        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText bday = (EditText) findViewById(R.id.editText2);
        final EditText email = (EditText) findViewById(R.id.editText3);
        final EditText tel = (EditText) findViewById(R.id.editText4);
        final EditText addr = (EditText) findViewById(R.id.editText7);
        final Button btnNext = (Button) findViewById(R.id.button);

        final EditText user = (EditText) findViewById(R.id.editText8);
        final EditText pass = (EditText) findViewById(R.id.editText9);
        final ImageView pic = (ImageView) findViewById(R.id.imageView3);



        pic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null)  {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);}

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        break;
                    }
                }
                return true;
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View v)
                                       {
                                           final String Name = name.getText().toString();
                                           final String Bday = bday.getText().toString();
                                           final String Email = email.getText().toString();
                                           final String Tel = tel.getText().toString();
                                           final String Addr = addr.getText().toString();
                                           final String User = user.getText().toString();
                                           final String Pass = pass.getText().toString();

                                           // bitmap to byte array
                                           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                           bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                           //byte array to base64
                                           byte[] byteArray = byteArrayOutputStream .toByteArray();
                                           // base64 to string
                                           final String photo = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                           Response.Listener<String> responseListener = new Response.Listener<String>() {

                                               @Override
                                               public void onResponse(String response){
                                                   try {
                                                       JSONObject jsonResponse = new JSONObject(response);
                                                       boolean success = jsonResponse.getBoolean("success");
                                                       if(success)
                                                       {
                                                           Intent intent = new Intent(Sign_Up.this,Login.class);
                                                           Sign_Up.this.startActivity(intent);
                                                       }
                                                       else
                                                       {
                                                           AlertDialog.Builder builder = new AlertDialog.Builder(Sign_Up.this);
                                                           builder.setMessage("Register failed")
                                                                   .setNegativeButton("Retry", null)
                                                                   .create()
                                                                   .show();
                                                       }
                                                   }
                                                   catch (JSONException e)
                                                   {
                                                       e.printStackTrace();
                                                   }
                                               }
                                           };
                                           RegisterRequest registerRequest = new RegisterRequest(Name,Bday,Email,Tel,Addr,User,Pass,photo,responseListener);

                                           RequestQueue queue = Volley.newRequestQueue(Sign_Up.this);
                                           queue.add(registerRequest);
                                       }
                                   }
        );
    }

    public void btnNext(View view)
    {
        Intent intent = new Intent(Sign_Up.this, Home.class);
        startActivity(intent);
    }
}

