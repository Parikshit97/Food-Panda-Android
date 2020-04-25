package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class KFCtablehall extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    HallTableDatabase pdba;
    BookingsDatabase bdba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kfctablehall);
        pdba=new HallTableDatabase(this);
        bdba=new BookingsDatabase(this);
    }

    public void adbview2(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor res=pdba.getAllData();
        while(res.moveToNext()) {
            if (res.getString(0).equals("KFC")) {
                buffer.append("Restaurant: " + res.getString(0) + "\n");
                buffer.append("Table: " + res.getString(1) + "\n");
                buffer.append("Hall: " + res.getString(2) + "\n\n");
            }
        }
        AlertDialog.Builder alertDialBuilder=new AlertDialog.Builder(this);
        alertDialBuilder.setTitle("TABLE/HALL VACANCY");
        alertDialBuilder.setMessage(buffer);
        alertDialBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
//        alertDialBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });

        AlertDialog alertDialog=alertDialBuilder.create();
        alertDialog.show();
    }


    public void opendatetimepicker(View view) {
        DialogFragment datePicker=new DateClassFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }

    public void okgeneratetoast(View view) {
        EditText tableet=(EditText)findViewById(R.id.ettableno);
        EditText hallnoet=(EditText)findViewById(R.id.ethallno);
        TextView datetv=(TextView)findViewById(R.id.etdate);
        boolean result=bdba.insertData("Parikshit Narang",tableet.getText().toString(),hallnoet.getText().toString(),datetv.getText().toString());
        if(result==true) {
            Toast.makeText(this, "Booking confirmed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Booking not confirmed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentdateString=DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView tv=(TextView)findViewById(R.id.etdate);
        tv.setText(currentdateString);
    }

}
