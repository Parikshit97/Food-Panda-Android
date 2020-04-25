package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class FeedbackDialogBox extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        builder.setMessage("Thanks for your valuable feedback!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
