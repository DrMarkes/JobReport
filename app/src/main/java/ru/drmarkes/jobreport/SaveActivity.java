package ru.drmarkes.jobreport;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SaveActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    static final String YEAR = "year";
    static final String MONTH = "month";
    static final String DAY = "day";

    int year;
    int month;
    int day;

    private TextView dateTextView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        dateTextView = (TextView)findViewById(R.id.dateTextView);
        calendar = Calendar.getInstance(TimeZone.getDefault());

        if(savedInstanceState != null) {
            year = savedInstanceState.getInt(YEAR);
            month = savedInstanceState.getInt(MONTH);
            day = savedInstanceState.getInt(DAY);
            calendar.set(year, month, day);
        } else {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        showDate();
    }

    public void onClick(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        calendar.set(year, monthOfYear, dayOfMonth);
        showDate();
    }

    private void showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy _ HH:mm:ss", Locale.getDefault());
        Date date = calendar.getTime();
        dateTextView.setText(simpleDateFormat.format(date));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanseState) {
        savedInstanseState.putInt(YEAR, year);
        savedInstanseState.putInt(MONTH, month);
        savedInstanseState.putInt(DAY, day);

        super.onSaveInstanceState(savedInstanseState);
    }
}
