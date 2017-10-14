package stasbizdiga.pamlab2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CalendarView simpleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get reference
        //simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the color for the dates of  focused month

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }

}

