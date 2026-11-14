package com.dineops.app;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Compact, readable dialogs using the same surfaces and actions as the app. */
final class UiDialog {
    static Dialog create(Context context, String title, String message) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        int padding = (int) (24 * context.getResources().getDisplayMetrics().density);
        LinearLayout content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(padding, padding, padding, padding);
        content.setBackgroundResource(R.drawable.ui_card);
        TextView heading = new TextView(context);
        heading.setText(title);
        heading.setTextSize(25);
        heading.setTextColor(context.getResources().getColor(R.color.ui_ink));
        heading.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL));
        content.addView(heading);
        TextView body = new TextView(context);
        body.setText(message);
        body.setTextSize(16);
        body.setTextColor(context.getResources().getColor(R.color.ui_muted));
        body.setLineSpacing(padding / 3f, 1f);
        body.setPadding(0, padding, 0, padding);
        content.addView(body);
        Button close = new Button(context);
        close.setText(context.getString(R.string.message_47));
        close.setAllCaps(false);
        close.setTextColor(Color.WHITE);
        close.setBackgroundResource(R.drawable.ui_button);
        close.setOnClickListener(v -> dialog.dismiss());
        content.addView(close, new LinearLayout.LayoutParams(-1, padding * 2 + padding / 3));
        dialog.setContentView(content);
        dialog.setOnShowListener(ignored -> {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(context.getResources().getDisplayMetrics().widthPixels - padding * 2, -2);
        });
        return dialog;
    }
}
