package stasbizdiga.pamlab2;


import Utils.FileHandler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static stasbizdiga.pamlab2.Alarm.timeConvert;

public class EditActivity extends AppCompatActivity {

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


        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        final EditText editNote = (EditText) findViewById(R.id.editNote);
        JSONTokener reader = new JSONTokener(FileHandler.readFromFile(getApplicationContext(), date));
        try {
            JSONObject object = (JSONObject) reader.nextValue();
            String jsonnote = object.getString("note");
            editNote.setText(jsonnote);

            String jsontime[] = object.getString("time").split(":");
            timePicker.setCurrentHour(Integer.valueOf(jsontime[0]));
            timePicker.setCurrentMinute(Integer.valueOf(jsontime[1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    private void Save() {
        EditText editNote = (EditText) findViewById(R.id.editNote);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        JSONObject saveBlock = new JSONObject();
        try {
            saveBlock.put("date", date);
            saveBlock.put("note", editNote.getText().toString());
            saveBlock.put("time", timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FileHandler.writeToFile(saveBlock.toString(), date, getApplicationContext());

        cancelAlert(getApplicationContext());

        startAlert(timeConvert(date,timePicker.getCurrentHour(),timePicker.getCurrentMinute()),editNote.getText().toString());

    }


    void cancelAlert(Context context) {

        Alarm.cancelAlarm(context);
    }

    private void startAlert(long time, String note)
    {
        Alarm alarm = new Alarm();
        alarm.setAlarm(this, time, note);
    }
}


