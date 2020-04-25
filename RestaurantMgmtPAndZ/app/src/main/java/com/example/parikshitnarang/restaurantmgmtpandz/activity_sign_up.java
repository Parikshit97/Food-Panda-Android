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

public class activity_sign_up extends AppCompatActivity {

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
        if(Integer.parseInt(otp.getText().toString())==randomNumber){
            Toast.makeText(this,"OTP Validation successfull...",Toast.LENGTH_SHORT).show();
            String nameet=name.getText().toString();
            String emailet=email.getText().toString();
            String phoneet=phone.getText().toString();
           boolean isInserted= dba.insertData(nameet,phoneet,emailet);
//           admin newadmin=new admin(dba);
           if(isInserted){
               Toast.makeText(this,"Data sent to the server successfull!",Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this,"Data sent to the server failed!",Toast.LENGTH_SHORT).show();
           }

        }else{
            Toast.makeText(this,"OTP Validation failed...",Toast.LENGTH_SHORT).show();
        }

    }

    public void generateOTP(View view) {
        EditText name=(EditText)findViewById(R.id.editName);
        EditText phone=(EditText)findViewById(R.id.editPhone);
        Random random=new Random();
        randomNumber= random.nextInt(1000000);

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(activity_sign_up.this,CHANNEL_ID);
                notificationBuilder.setContentTitle("Hey "+name.getText().toString()+", Your OTP is:");
                notificationBuilder.setSmallIcon(R.drawable.iconresized);
                notificationBuilder.setContentText(""+randomNumber);
                notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,notificationBuilder.build());


    }
}
//    EditText name=(EditText)findViewById(R.id.editName);
//    EditText phone=(EditText)findViewById(R.id.editPhone);
//    Random random=new Random();
//    int radint=random.nextInt(1000000);
//    NotificationCompat.Builder builder =
//            new NotificationCompat.Builder(this)
//                    .setContentTitle("Hey "+name.getText().toString()+" OTP is here:")
//                    .setContentText(""+Integer.toString(radint));
//
//    Intent notificationIntent = new Intent(this, activity_sign_up.class);
//    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//                // Add as notification
//                NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
//                manager.notify(0, builder.build());