package com.example.parikshitnarang.restaurantmgmtpandz;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ScrollView;
import java.io.File;
import java.io.FileOutputStream;

/** Captures real activities without adding preview routes to the shipped app. */
public class ScreenshotRunner extends Instrumentation {
    private String phase;
    private String selectedScreen;

    @Override public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        phase = arguments.getString("phase", "after");
        selectedScreen = arguments.getString("screen");
        start();
    }

    @Override public void onStart() {
        Bundle result = new Bundle();
        String[] screens = {"MainActivity", "activity_sign_in", "activity_sign_up", "useroptions",
            "offerview", "resturantinfo", "order", "KFC", "cartview", "bill", "tablehall",
            "KFCtablehall", "feedback", "admin", "custdbhandler", "restdbhandler",
            "pricedbhandler", "orderdbhandler", "bookingview"};
        try {
            File directory = new File(getTargetContext().getExternalFilesDir(null), "screenshots/" + phase);
            directory.mkdirs();
            for (int i = 0; i < screens.length; i++) {
                if (selectedScreen != null && !selectedScreen.equals(screens[i])) continue;
                Intent intent = new Intent().setClassName(getTargetContext(),
                    getTargetContext().getPackageName() + "." + screens[i]);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Activity activity = startActivitySync(intent);
                waitForIdleSync();
                SystemClock.sleep(1000);
                ScrollView scroll = (ScrollView) ((android.view.ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
                runOnMainSync(() -> scroll.scrollTo(0, 0));
                SystemClock.sleep(250);
                capture(directory, String.format("%02d-%s.png", i + 1, screens[i]));
                int bottom = scroll.getChildAt(0).getHeight() - scroll.getHeight();
                if (bottom > 32 && phase.equals("after")) {
                    runOnMainSync(() -> scroll.scrollTo(0, bottom));
                    SystemClock.sleep(250);
                    capture(directory, String.format("%02d-%s-bottom.png", i + 1, screens[i]));
                    runOnMainSync(() -> scroll.scrollTo(0, 0));
                }
                if (phase.equals("after") && screens[i].equals("useroptions")) {
                    runOnMainSync(() -> activity.findViewById(R.id.buttonContactUs).performClick());
                    waitForIdleSync();
                    SystemClock.sleep(350);
                    capture(directory, "20-contact-dialog.png");
                }
                if (phase.equals("after") && screens[i].equals("feedback")) {
                    runOnMainSync(() -> ((feedback) activity).dialogopenforsubmission(null));
                    waitForIdleSync();
                    SystemClock.sleep(350);
                    capture(directory, "21-feedback-dialog.png");
                }
                runOnMainSync(activity::finish);
            }
            result.putString("screenshots", directory.getAbsolutePath());
            result.putString("result", "Captured all 19 activities successfully");
            finish(Activity.RESULT_OK, result);
        } catch (Exception error) {
            result.putString("error", android.util.Log.getStackTraceString(error));
            finish(Activity.RESULT_CANCELED, result);
        }
    }

    private void capture(File directory, String filename) throws Exception {
        Bitmap bitmap = getUiAutomation().takeScreenshot();
        if (bitmap == null) throw new IllegalStateException("Screenshot unavailable: " + filename);
        try (FileOutputStream output = new FileOutputStream(new File(directory, filename))) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        }
        bitmap.recycle();
    }
}
