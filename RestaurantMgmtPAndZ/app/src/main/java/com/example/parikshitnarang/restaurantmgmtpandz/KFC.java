package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class KFC extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String total;
    int gtotal;
    PriceDatabase pdba;
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kfc);
        pdba=new PriceDatabase(this);

    }


    public void generateMenu(View view) {
        Spinner spinner=(Spinner)findViewById(R.id.spinner1);
        RadioButton vegrb=(RadioButton)findViewById(R.id.vegrb);
        RadioButton nonvegrb=(RadioButton)findViewById(R.id.nonvegrb);
        RadioButton bevrb=(RadioButton)findViewById(R.id.beverage);
        if(vegrb.isChecked()){
            ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.veg,R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String text=parent.getItemAtPosition(position).toString();
                    TextView etorder=(TextView)findViewById(R.id.etorder);
                    EditText etqty=(EditText)findViewById(R.id.etqty);
                    etorder.setText(text);
                    Cursor res=pdba.getAllData();
                    while(res.moveToNext()){
                        if(res.getString(1).equals(text)){
                            gtotal=gtotal+(Integer.parseInt(etqty.getText().toString())*Integer.parseInt(res.getString(2)));
                        }
                    }
                    total=Integer.toString(gtotal);
                    TextView gt=(TextView) findViewById(R.id.ettotal);
                    gt.setText("Rs."+total);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else if(nonvegrb.isChecked()){
            ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.nonveg,R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String text=parent.getItemAtPosition(position).toString();
                    TextView etorder=(TextView)findViewById(R.id.etorder);
                    EditText etqty=(EditText)findViewById(R.id.etqty);
                    etorder.setText(text);
                    Cursor res=pdba.getAllData();
                    while(res.moveToNext()){
                        if(res.getString(1).equals(text)){
                            gtotal=gtotal+(Integer.parseInt(etqty.getText().toString())*Integer.parseInt(res.getString(2)));
                        }
                    }
                    total=Integer.toString(gtotal);
                    TextView gt=(TextView) findViewById(R.id.ettotal);
                    gt.setText("Rs."+total);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(bevrb.isChecked()){
            ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.beverage,R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String text=parent.getItemAtPosition(position).toString();
                    TextView etorder=(TextView)findViewById(R.id.etorder);
                    EditText etqty=(EditText)findViewById(R.id.etqty);
                    etorder.setText(text);
                    Cursor res=pdba.getAllData();
                    while(res.moveToNext()){
                        if(res.getString(1).equals(text)){
                            gtotal=gtotal+(Integer.parseInt(etqty.getText().toString())*Integer.parseInt(res.getString(2)));
                        }
                    }
                    total=Integer.toString(gtotal);
                    TextView gt=(TextView) findViewById(R.id.ettotal);
                    gt.setText("Rs."+total);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }



    }

    public void addToCart(View view) {
        TextView item=(TextView)findViewById(R.id.etorder);
        EditText qty=(EditText)findViewById(R.id.etqty);
        TextView dd=(TextView) findViewById(R.id.etdate);

        CartDatabase cdb=new CartDatabase(this);
        boolean res=cdb.insertData("Parikshit Narang",item.getText().toString(),qty.getText().toString(),dd.getText().toString(),total);
        if(res==true){
            Toast.makeText(this,"Data sent", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data not sent", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewCart(View view) {

        Intent intent=new Intent(getApplicationContext(),cartview.class);
        startActivity(intent);
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

    public void onButtonClick(View view) {
        DialogFragment datePicker=new DateClassFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }
}
