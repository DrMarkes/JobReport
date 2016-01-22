package ru.drmarkes.jobreport;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Андрей on 22.01.2016.
 */
public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (DatePickerDialog.OnDateSetListener) activity;
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
