Push-Up Progress Tracker
A simple Android app built using Jetpack Compose in Android Studio to help you track your daily push-up progress. The app encourages you to increase your push-up count each day and penalizes skipped days by decreasing your progress.

Features
Main Button: A large circular button displaying your current push-up count. Press it once per day to increment your goal.
Fail Button: Records a fail for the day, preventing further increments until the next day.
Reset Button: Resets your push-up count to the starting value (15).
Streak Counter: Tracks the number of consecutive successful days.
Statistics: Displays total successes, failures, and your success rate.
Motivational Messages: Provides random motivational messages to keep you encouraged.
Persistent Data: Saves your progress locally, so it's maintained even after closing the app.
Requirements
Android Studio Flamingo or newer
Android SDK version 21 or higher
Kotlin programming language
Jetpack Compose UI toolkit
Getting Started
1. Clone the Repository
If you have a repository, clone it using:

bash
Copy code
git clone https://github.com/yourusername/push-up-progress-tracker.git
2. Open the Project in Android Studio
Launch Android Studio.
Click on File > Open.
Navigate to the project directory and select it.
3. Sync Project with Gradle Files
Android Studio should prompt you to sync the project.
If not, click on File > Sync Project with Gradle Files.
4. Enable Java 8 Time API Desugaring
To use java.time.LocalDate and other Java 8 Time APIs on lower API levels:

In your build.gradle (Module: app) file, add:

gradle
Copy code
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
        coreLibraryDesugaringEnabled true
    }
    // ...
}

dependencies {
    // ...
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:2.0.3'
}
5. Build and Run the App
Connect your Android device with USB Debugging enabled.
Click the Run button (green triangle) or go to Run > Run 'app'.
Select your device from the list.
The app should build and install on your device.
Usage
Incrementing Push-Up Count:

Press the main circular button once per day to increase your push-up count.
If you try to press it again on the same day, a message will prompt you to wait until tomorrow.
Recording a Fail:

Press the Fail button if you miss a day or can't meet your goal.
This resets your streak and prevents incrementing until the next day.
Resetting Progress:

Press the Reset button to set your push-up count back to 15.
Useful if you want to start over.
Viewing Statistics:

The app displays your current streak, total successes, total failures, and success rate at the top.
Motivational Messages:

Before you press the main button each day, a random motivational message is displayed.
After pressing the button or recording a fail, a success or fail message appears.
Customization
Adding More Messages
Open MainActivity.kt.

Locate the companion object containing motivationalMessages, successMessages, and failMessages.

Add more messages to each list as desired.

kotlin
Copy code
companion object {
    val motivationalMessages = listOf(
        "Push beyond the limits!",
        "You're a powerhouse!",
        // Add more messages here
    )
    // ...
}
Changing the Starting Count
In MainActivity.kt, find the initial value of pressUpCountState:

kotlin
Copy code
var pressUpCountState by remember { mutableStateOf(15) }
Change 15 to your desired starting number.
