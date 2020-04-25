package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class useroptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useroptions);
    }

    public void dialogopen(View view){
        ContactDialogBox contactDialogBox=new ContactDialogBox();
        contactDialogBox.show(getSupportFragmentManager(),"Contact us");
    }

    public void takefeedback(View view) {
        Intent feedbackintent=new Intent(this,feedback.class);
        startActivity(feedbackintent);
    }

    public void takeOrder(View view) {
        Intent orderintent=new Intent(this,order.class);
        startActivity(orderintent);
    }

    public void openrestaurants(View view) {
        Intent restintent=new Intent(this,resturantinfo.class);
        startActivity(restintent);
    }

    public void offerview(View view) {
        Intent restintent=new Intent(this,offerview.class);
        startActivity(restintent);
    }

    public void booktablehall(View view) {
        Intent restintent=new Intent(this,tablehall.class);
        startActivity(restintent);
    }
}
