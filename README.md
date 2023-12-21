######
<div align = center><img src="https://raw.githubusercontent.com/TarushGupta23/storage/main/gdscbanner.png?token=GHSAT0AAAAAACIOGP7ALN6ZX73PIY6YURUMZMECQLA"><br><br>

&ensp;[<kbd> <br> Overview <br> </kbd>](#Overview)&ensp;
&ensp;[<kbd> <br> Features <br> </kbd>](#Features)&ensp;
&ensp;[<kbd> <br> Technologies <br> </kbd>](#TechnologiesUsed)&ensp;
&ensp;[<kbd> <br> Installation <br> </kbd>](#GettingStarted)&ensp;
&ensp;[<kbd> <br> Contributions <br> </kbd>](#Contributions)&ensp;
&ensp;[<kbd> <br> Acknowledgments <br> </kbd>](#Acknowledgments)&ensp;
<br><br><br><br></div>
## Overview
# *Attendence Tracking App*
Welcome to the Attendance Tracker App, a project created under `GDSC (Google Developer Student Clubs)`. This app is designed to help users keep track of their attendance for multiple subjects conveniently. It incorporates features such as subject management, class attendance, and missed class tracking. Additionally, the app includes authentication functionalities (login and signup) and utilizes Firebase for database connectivity.

## Features 🚀

1. **User Authentication:**
   - Users can sign up for a new account or log in with their existing credentials.
   - Firebase authentication ensures secure user access.

2. **Subject Management:**
   - Users can add, edit, and delete subjects.
   - Each subject has two fields: classAttended and classMissed.

3. **Attendance Tracking:**
   - For each subject, users can mark attendance by clicking the "Class Attended" button.
   - The "Class Missed" button allows users to record missed classes.

4. **Firebase Database Connectivity:**
   - The app is connected to Firebase, enabling real-time data storage and retrieval.
   - Subject data, user information, and attendance records are stored securely in the Firebase Realtime Database.

## Technologies Used 🛠️

- **Firebase:**
  - Authentication: Firebase Authentication is used for user login and signup.
  - Realtime Database: Firebase Realtime Database is employed for storing and retrieving subject and attendance data.

- **Android Studio:**
  - The app is developed using Android Studio, the official IDE for Android app development.

## Getting Started 💻

To run the Attendance Tracker App locally, follow these steps:

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/attendance-tracker-app.git
   ```

2. Open the project in Android Studio.

3. Configure Firebase:
   - Create a Firebase project on the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app to your Firebase project and follow the setup instructions to obtain the `google-services.json` file.
   - Place the `google-services.json` file in the `app` directory of your Android Studio project.

4. Build and run the app on an emulator or physical device.

## Contributions 🤝

Contributions to the Attendance Tracker App project are welcome. If you have ideas for improvements or find any issues, feel free to open an issue or submit a pull request.

## Acknowledgments

- GDSC (Google Developer Student Clubs) for the opportunity and support in creating this project.
- Firebase for providing robust authentication and database solutions.
- Android Studio for the development environment.

Feel free to reach out if you have any questions or need further assistance!
