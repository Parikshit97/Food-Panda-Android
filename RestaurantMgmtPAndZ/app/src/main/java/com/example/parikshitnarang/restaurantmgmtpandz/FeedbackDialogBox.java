package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class FeedbackDialogBox extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return UiDialog.create(getActivity(), getString(R.string.message_45),
            getString(R.string.message_46));
    }
}
