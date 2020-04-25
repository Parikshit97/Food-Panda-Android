package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DateClassFragment extends DialogFragment {
    Calendar c=Calendar.getInstance();
    int year=c.get(Calendar.YEAR);
    int month=c.get(Calendar.MONTH);
    int day=c.get(Calendar.DAY_OF_MONTH);
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
    }
}
