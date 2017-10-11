package stasbizdiga.pamlab1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    static Bitmap imageBitmap;
    static boolean photoTaken = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            photoTaken = true;
            ImageView photo = (ImageView) findViewById(R.id.photo);
            photo.setImageBitmap(imageBitmap);
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();
        final EditText textToSearch = (EditText) findViewById(R.id.editTextSearch);
        final Button notifyButton = (Button) findViewById(R.id.button1);
        final Button searchButton = (Button) findViewById(R.id.button2);
        final Button cameraButton = (Button) findViewById(R.id.button3);
        final Button extraButton = (Button) findViewById(R.id.button4);
        final RadioGroup typeOfCam = (RadioGroup) findViewById(R.id.camType);
        final Toast toast = Toast.makeText(context, "10s passed!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);


        // T O A S T   B U T T O N
        notifyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() { //run the next code snippet after 10s
                        toast.show();
                    }
                }, 10000);
            }
        });

        // S E A R C H   B U T T O N
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    try {
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        String googledString = textToSearch.getText().toString();
                        intent.putExtra(SearchManager.QUERY, googledString);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(context, "Unknown error!", Toast.LENGTH_SHORT).show();
                    }


            }
        });

        // C A M E R A   B U T T O N
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                int id = typeOfCam.getCheckedRadioButtonId();

                switch(id){
                    case R.id.radioButton1:
                        takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                    break;

                    case R.id.radioButton2:
                        takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                    break;

                    default:
                    break;}

                if (takePictureIntent.resolveActivity(getPackageManager()) != null)  {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);}
            }
        });

        // E X T R A   B U T T O N
        extraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(photoTaken)
                {
                    Intent intent = new Intent(MainActivity.this, Preview.class);
                    intent.putExtra("pic", imageBitmap);
                    startActivity(intent);
                }
                else{Toast.makeText(context, "Take a photo first!", Toast.LENGTH_SHORT).show();}
                    }
                });
            }

}
