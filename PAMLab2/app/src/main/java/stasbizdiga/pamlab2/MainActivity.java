package stasbizdiga.pamlab2;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Utils.FileHandler;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "debug_log";
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    static String todayDate = df.format(Calendar.getInstance().getTime());
    static String selectedDate = todayDate;
    static String today = "Today";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Context context = getApplicationContext();
        final EditText textToSearch = (EditText) findViewById(R.id.editTextSearch);
        final Button addButton = (Button) findViewById(R.id.btnAdd);
        final Button editButton = (Button) findViewById(R.id.btnEdit);
        final Button deleteButton = (Button) findViewById(R.id.btnDel);
        final Button searchButton = (Button) findViewById(R.id.btnSearch);
        final CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get reference
        final Calendar selected = Calendar.getInstance();


        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month+1) + "-" + dayOfMonth;

                selected.setTimeInMillis(simpleCalendarView.getDate());

                int day = selected.get(Calendar.DAY_OF_WEEK);
                switch (day) {
                    case Calendar.MONDAY:
                        today = "Monday";
                        break;
                    case Calendar.TUESDAY:
                        today = "Tuesday";
                        break;
                    case Calendar.WEDNESDAY:
                        today = "Wednesday";
                        break;
                    case Calendar.THURSDAY:
                        today = "Thursday";
                        break;
                    case Calendar.FRIDAY:
                        today = "Friday";
                        break;
                    case Calendar.SATURDAY:
                        today = "Saturday";
                        break;
                    case Calendar.SUNDAY:
                        today = "Sunday";
                        break;
                    default:
                        today = "-";
                        break;
                }
            }
        });

        // A D D   B U T T O N
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                  if (file exists){
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("day", today);
                startActivity(intent);
//                } else {
//                    Toast.makeText(context, "Already added!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        // E D I T   B U T T O N
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("date", selectedDate);
                intent.putExtra("day", today);
                startActivity(intent);
            }
        });


        // D E L E T E   B U T T O N
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Alarm.cancelAlarm(context);

                deleteFile(selectedDate);
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();

            }
        });


        // S E A R C H   B U T T O N
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String searchedString = textToSearch.getText().toString();
                String foundDate = Search(searchedString);

                String parts[] = foundDate.split("-");
                int day = Integer.parseInt(parts[2]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[0]);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month-1); // 0=jan, 11=dec
                calendar.set(Calendar.DAY_OF_MONTH, day);

                long milliTime = calendar.getTimeInMillis();
                simpleCalendarView.setDate(milliTime, true, true);

            }
        });


    }

    String Search(CharSequence keyword) {
        DateTime a = new DateTime(selectedDate),
                b = new DateTime("2019-12-01");

        for (DateTime date = a.plusDays(1); date.isBefore(b); date = date.plusDays(1)) {
                if ((FileHandler.readFromFile(getApplicationContext(), date.toString("yyyy-MM-dd"))).contains(keyword)) {
                    Toast.makeText(getApplicationContext(), FileHandler.readFromFile(getApplicationContext(), date.toString("yyyy-MM-dd")), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),  date.toString("yyyy-MM-dd"), Toast.LENGTH_SHORT).show();

                    return date.toString("yyyy-MM-dd");
                }

        }
        Toast.makeText(getApplicationContext(), "Nothing found!", Toast.LENGTH_SHORT).show();
        return todayDate;
    }




    @Override
    protected void onStart() {
        super.onStart();

    }

}

