package ru.drmarkes.jobreport;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import ru.drmarkes.jobreport.provider.ContractClass;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private DataAdapter dataAdapter;
    private String dayImput;
    private TextView dateTextView;
    private Calendar calendar;
    private final String day = "day = ?";
    private String[] selection;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        calendar = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        dayImput = simpleDateFormat.format(calendar.getTime());

        dateTextView = (TextView)findViewById(R.id.dateTextView);
        showDate();

        dataAdapter = new DataAdapter(this, null, 0);

        listViewItems = (ListView) findViewById(R.id.listViewItems);
        listViewItems.setAdapter(dataAdapter);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent saveIntent = new Intent(this, SaveActivity.class);
                startActivity(saveIntent);
                break;
            case R.id.dateTextView:
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "datePicker");
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        selection = new String[] {dayImput};
        Toast.makeText(this, dayImput, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        return new CursorLoader(
                this,
                ContractClass.Job.CONTENT_URI,
                ContractClass.Job.DATA_PROJECTION,
                day,
                selection,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dataAdapter.swapCursor(data);
        Toast.makeText(this, "ОБновляем", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataAdapter.swapCursor(null);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        showDate();
        changeDayImput();
    }

    private void changeDayImput() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        dayImput = simpleDateFormat.format(calendar.getTime());
  //      Toast.makeText(this, dayImput, Toast.LENGTH_LONG).show();
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        Date date = calendar.getTime();
        dateTextView.setText(simpleDateFormat.format(date));

        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        dayImput = simpleDateFormat.format(calendar.getTime());
    }
}
