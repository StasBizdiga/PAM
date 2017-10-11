package stasbizdiga.pamlab1;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Preview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);

        ImageView imageFrame = (ImageView) findViewById(R.id.preview);
        Bitmap photo = (Bitmap) getIntent().getExtras().get("pic");

        imageFrame.setImageBitmap(photo);


    }

}
