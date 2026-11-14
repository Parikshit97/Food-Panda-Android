# Food Panda Android UI refresh

The `ui/food-panda-refresh` branch redesigns all 19 activities and the contact and feedback dialogs. Open [the offline screenshot gallery](screenshots/index.html) for the complete review, with original screenshots and the lower sections of scrolling pages. Images come from the running Android app, not mockups.

The app uses warm cream surfaces, pink primary actions, burgundy headings, rounded cards, custom vector illustrations and icons, persistent input labels, and shared back navigation. Restaurant selection now uses cards. Sign-in fields match the existing name-and-phone authentication instead of describing the name as a password. Sample credentials are no longer filled in. Duplicate offer cards are consolidated into the two distinct existing offers.

Build tooling was updated to Android Gradle Plugin 8.5.2 and Gradle 8.9. The app compiles and targets API 34, supports Android 5.0/API 21 and later, and uses Java 17 source compatibility. Android's version-specific light system-bar styles keep older devices readable. Registration now validates an empty verification code and creates a notification channel with the required notification permission flow.

## Build and review

Use Android Studio's bundled JDK 21, or a compatible installed JDK, and install Android SDK Platform 34.

```sh
cd RestaurantMgmtPAndZ
./gradlew assembleDebug assembleDebugAndroidTest lintDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb install -r app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
adb shell am instrument -w -e phase after \
  com.example.parikshitnarang.restaurantmgmtpandz.test/com.example.parikshitnarang.restaurantmgmtpandz.ScreenshotRunner
cd ..
adb pull /sdcard/Android/data/com.example.parikshitnarang.restaurantmgmtpandz/files/screenshots/after screenshots/
python3 tools/build_screenshot_gallery.py
```

The screenshot runner lives only in the instrumentation test package. It can open each activity for review without introducing exported preview activities or authentication bypasses into the app. On long pages it captures both the top and bottom of the actual scroll view.

## Scope and existing behavior

This remains the original local SQLite restaurant-management app. The UI refresh does not add a backend, payment service, or production authentication. Ordering and booking routes remain available for KFC; other restaurants remain directory entries. The original cart quantity update handler and several confirmation handlers are unfinished business logic and are not completed by this visual refresh. The feedback dialog is an acknowledgement, not a newly connected submission service. API 34 is the tested target here; publishing to an app store may require a separate target-SDK update and release review.

Before screenshots were captured from the original UI with the minimum build-tool changes needed to run it. After screenshots use the redesigned app. The refresh is maintained on the `ui/food-panda-refresh` branch.
