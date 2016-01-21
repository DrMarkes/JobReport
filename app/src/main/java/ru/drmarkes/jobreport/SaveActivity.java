package ru.drmarkes.jobreport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        TextView dateTextView = (TextView)findViewById(R.id.dateTextView);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy _ hh:mm:ss a", Locale.getDefault());
        dateTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
