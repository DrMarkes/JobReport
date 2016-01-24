package ru.drmarkes.jobreport;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SaveActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    static final String YEAR = "year";
    static final String MONTH = "month";
    static final String DAY = "day";

    int year;
    int month;
    int day;

    String name;
    String number;

    private TextView dateTextView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        FloatingActionButton save = (FloatingActionButton) findViewById(R.id.save);
        save.setOnClickListener(this);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        calendar = Calendar.getInstance(TimeZone.getDefault());

        if (savedInstanceState != null) {
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
        initSpinner();
        initEditText();
    }

    private void initEditText() {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        name = nameEditText.getText().toString();

        EditText numberEditText = (EditText) findViewById(R.id.numberEditText);
        number = numberEditText.getText().toString();
    }

    private void initSpinner() {
        Spinner spinnerOrder = (Spinner) findViewById(R.id.spinnerOrder);
        ArrayAdapter<CharSequence> adapterOrder = ArrayAdapter.createFromResource(
                this, R.array.order, android.R.layout.simple_spinner_item);
        adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapterOrder);

        Spinner spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
        ArrayAdapter<CharSequence> adapterDepartment = ArrayAdapter.createFromResource(
                this, R.array.department, android.R.layout.simple_spinner_item);
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapterDepartment);

        Spinner spinnerManipulation = (Spinner) findViewById(R.id.spinnerManipulation);
        ArrayAdapter<CharSequence> adapterManipulation = ArrayAdapter.createFromResource(
                this, R.array.manipulation, android.R.layout.simple_spinner_item);
        adapterManipulation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerManipulation.setAdapter(adapterManipulation);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dateTextView:
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.save:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
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
