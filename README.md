🚶 SAFEWALK — Personal Safety Android App

Your safety companion, every step of the way.

Show Image
Show Image
Show Image
Show Image

📌 Overview
SAFEWALK is an Android application designed to enhance personal safety for individuals walking alone — especially in urban areas like Coimbatore. The app provides real-time safety tools that allow users to quickly alert trusted contacts, share live location, and access emergency services in critical moments.

✨ Features

🆘 SOS / Panic Button — Instantly notify emergency contacts with a single tap
📍 Live Location Sharing — Share real-time GPS location with trusted contacts
📞 Emergency Contact Management — Add and manage personal emergency contacts
🗺️ Safe Route Suggestions — Navigate through safer, well-lit paths (if applicable)
🔔 Background Alerts — Runs silently in the background to stay ready at all times
🔒 Privacy Focused — Location data stays with your trusted contacts only


🛠️ Tech Stack
ComponentTechnologyLanguageJavaPlatformAndroidBuild SystemGradle 8.4.0IDEAndroid StudioMin SDKAndroid 6.0+ (API 23)

📁 Project Structure
SAFEWALK/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Java source files
│   │   │   ├── res/           # Layouts, drawables, strings
│   │   │   └── AndroidManifest.xml
│   └── build.gradle           # App-level build config
├── gradle/wrapper/            # Gradle wrapper
├── build.gradle               # Project-level build config
├── settings.gradle
└── README.md

🚀 Getting Started
Prerequisites

Android Studio (latest stable version)
Android SDK (API 23+)
Java 8 or above
A physical Android device or emulator

Installation

Clone the repository

bash   git clone https://github.com/CIPHERRAJ/SAFEWALK.git
   cd SAFEWALK

Open in Android Studio

Launch Android Studio
Select "Open an existing project"
Navigate to the cloned SAFEWALK folder


Sync Gradle

Let Android Studio sync all Gradle dependencies automatically
If prompted, update SDK tools


Run the App

Connect an Android device via USB (with USB debugging enabled), or launch an emulator
Click ▶️ Run in Android Studio




📱 Screenshots

(Add screenshots of the app here)

Home ScreenSOS AlertLocation Sharingscreenshot_home.pngscreenshot_sos.pngscreenshot_location.png

🔐 Permissions Required
The app requires the following Android permissions:

ACCESS_FINE_LOCATION — For real-time GPS tracking
SEND_SMS — For sending SOS alerts via SMS
CALL_PHONE — For direct emergency calling
FOREGROUND_SERVICE — For background location monitoring
READ_CONTACTS — For selecting emergency contacts


🤝 Contributing
Contributions are welcome! To contribute:

Fork the repository
Create a new branch: git checkout -b feature/your-feature
Commit your changes: git commit -m "Add your feature"
Push to the branch: git push origin feature/your-feature
Open a Pull Request


📄 License
This project is licensed under the MIT License — see the LICENSE file for details.

👨‍💻 Author
Vimal Raj — @CIPHERRAJ
