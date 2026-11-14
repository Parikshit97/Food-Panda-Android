package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class ContactDialogBox extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return UiDialog.create(getActivity(), "Let’s talk.",
            "Parikshit Narang\n+91-7503636097\nparikshit3097@gmail.com");
    }
}
