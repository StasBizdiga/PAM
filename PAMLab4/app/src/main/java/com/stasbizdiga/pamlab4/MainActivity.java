package com.stasbizdiga.pamlab4;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button start;
    Button stop;
    TextView statusText;
    ImageView progBar;
    int progress;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);
        statusText = (TextView) findViewById(R.id.statusText);
        progBar = (ImageView) findViewById(R.id.imageView);
        final Drawable drawable = progBar.getDrawable();
        progress = 0;

        // A S Y N C   T A S K
        final Runnable runnable = new Runnable() {
            public void run() {
                statusText.setText("[Status]: DONE!");
                progress = 1;
                handler.postDelayed(this, 5000);
            }
        };


        // S T A R T   B U T T O N
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress == 0)
                {
                    progBar.setVisibility(View.VISIBLE);
                    if (drawable instanceof Animatable)
                    {
                        ((Animatable) drawable).start();
                    }

                    statusText.setText("[Status]: PROGRESS...");
                    handler.postDelayed(runnable, 5000);
                }
            }

        });

        // S T O P   B U T T O N
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    progress = 0;
                    handler.removeCallbacks(runnable);
                    progBar.setVisibility(View.INVISIBLE);
                    statusText.setText("[Status]: STOP");
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).stop();
                    }

            }
        });

    }

}