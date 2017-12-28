package stasbizdiga.pamlab2;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.FileHandler;

import static stasbizdiga.pamlab2.Alarm.timeConvert;

public class AddActivity extends AppCompatActivity {


    static String day;
    static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Button saveButton = (Button) findViewById(R.id.btnSave);
        final Button cancelButton = (Button) findViewById(R.id.btnCancel);
        TextView Today = (TextView) findViewById(R.id.textView1);
        TextView weekday = (TextView) findViewById(R.id.textView2);

        day = (String) getIntent().getExtras().get("day");
        date = (String) getIntent().getExtras().get("date");
        Today.setText(date);
        weekday.setText(day);


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Save();   //save event
                finish(); //then exit activity
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish(); //exit activity
            }
        });
    }

    private void Save()
    {
        EditText editNote = (EditText) findViewById(R.id.editNote);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        String timeHoursMins = timePicker.getCurrentHour().toString()+ ":"+timePicker.getCurrentMinute().toString();

        JSONObject saveBlock = new JSONObject();
        try {
            saveBlock.put("date", date);
            saveBlock.put("note", editNote.getText().toString());
            saveBlock.put("time", timeHoursMins);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Writing to file  with name <date>
        FileHandler.writeToFile(saveBlock.toString(),date, getApplicationContext());
       //Setting the alarm

        startAlert(timeConvert(date,timePicker.getCurrentHour(),timePicker.getCurrentMinute()),editNote.getText().toString());

        }



        private void startAlert(long time, String note)
        {
            Alarm alarm = new Alarm();
            alarm.setAlarm(this, time, note);
        }

}

