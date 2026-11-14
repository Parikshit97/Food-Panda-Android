package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.NotificationManager;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class activity_sign_up extends UiActivity {

    private final String CHANNEL_ID="otp_message";
    private final int NOTIFICATION_ID=001;
    int randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }

    public void register(View view) {
        DatabaseAdmin dba=new DatabaseAdmin(this);
        EditText name=(EditText)findViewById(R.id.editName);
        EditText email=(EditText)findViewById(R.id.editEmail);
        EditText phone=(EditText)findViewById(R.id.editPhone);
        EditText otp=(EditText)findViewById(R.id.otp);
        if (name.getText().toString().trim().isEmpty() || phone.getText().toString().trim().isEmpty()
                || email.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.message_36), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!otp.getText().toString().matches("[0-9]{6}") || randomNumber == 0) {
            otp.setError(getString(R.string.message_37));
            return;
        }
        if(Integer.parseInt(otp.getText().toString())==randomNumber){
            Toast.makeText(this,getString(R.string.message_38),Toast.LENGTH_SHORT).show();
            String nameet=name.getText().toString();
            String emailet=email.getText().toString();
            String phoneet=phone.getText().toString();
           boolean isInserted= dba.insertData(nameet,phoneet,emailet);
//           admin newadmin=new admin(dba);
           if(isInserted){
               Toast.makeText(this,getString(R.string.message_39),Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this,getString(R.string.message_40),Toast.LENGTH_SHORT).show();
           }

        }else{
            Toast.makeText(this,getString(R.string.message_41),Toast.LENGTH_SHORT).show();
        }

    }

    public void generateOTP(View view) {
        if (android.os.Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            Toast.makeText(this, getString(R.string.message_42), Toast.LENGTH_LONG).show();
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(new android.app.NotificationChannel(
                CHANNEL_ID, getString(R.string.message_43), NotificationManager.IMPORTANCE_DEFAULT));
        }
        EditText name=(EditText)findViewById(R.id.editName);
        EditText phone=(EditText)findViewById(R.id.editPhone);
        Random random=new Random();
        randomNumber= 100000 + random.nextInt(900000);

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(activity_sign_up.this,CHANNEL_ID);
                notificationBuilder.setContentTitle(getString(R.string.verification_notification_title));
                notificationBuilder.setSmallIcon(R.drawable.iconresized);
                notificationBuilder.setContentText(""+randomNumber);
                notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,notificationBuilder.build());


    }
}
