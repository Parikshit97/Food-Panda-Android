package com.example.parikshitnarang.restaurantmgmtpandz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void dialogopenforsubmission(View view) {
        FeedbackDialogBox feedbackDialogBox=new FeedbackDialogBox();
        feedbackDialogBox.show(getSupportFragmentManager(),"Feedback");
    }
}
