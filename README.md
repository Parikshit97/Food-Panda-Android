# DineOps

An Android application for restaurant ordering, table and hall reservations, and administrative record management.

![DineOps interface](screenshots/overview.png)

## Features

- Customer registration and sign-in
- Restaurant directory and menu selection
- Shopping cart and order summaries
- Table and hall reservations
- Customer, restaurant, pricing, and reservation records
- Feedback and contact information

## Interface review

The [screenshot gallery](screenshots/index.html) includes all 19 screens, two dialogs, and the lower sections of scrolling pages. Original screenshots are retained for historical comparison.

## Development

The Android project is located in `RestaurantMgmtPAndZ`. It uses Java, Android Gradle Plugin 8.5.2, Gradle 8.9, and Android SDK Platform 34. Android 5.0 or later is required.

```sh
cd RestaurantMgmtPAndZ
./gradlew assembleDebug assembleDebugAndroidTest lintDebug
```

See [UI-REFRESH.md](UI-REFRESH.md) for build instructions, screenshot capture, validation, and current functional limitations.
