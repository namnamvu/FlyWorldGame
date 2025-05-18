FlyHome - Java Desktop Game

FlyHome is a simple yet engaging maze navigation game built using Java's Swing and AWT libraries. The player controls a fly, navigating through a grid-based maze while avoiding predators and aiming to reach the home tile safely.

📁 Project Structure

FlyHome/
├── bin/                # Compiled .class files
├── img/                # Game images
├── src/                # Source code
│   ├── FlyWorldLauncher.java   # Game launcher
│   ├── FlyWorldGUI.java        # Game GUI
│   └── FlyWorld.java           # Game logic
├── manifest.MF         # Manifest file for .jar packaging
└── FlyHome.app         # macOS application bundle (if packaged)

🚀 Features

Grid-based maze gameplay

Arrow key controls for intuitive navigation

Visual feedback for success (reaching home) and failure (being eaten)

Lightweight and responsive Swing-based UI

Self-contained macOS app packaging using jpackage

🛠️ Technologies Used

Languages: Java

Libraries/Frameworks: Swing, AWT, Java I/O, Java ImageIO

Tools: jpackage, BufferedImage

📦 Packaging and Deployment

To package this project into a macOS app, use the jpackage tool:

jpackage \
  --name FlyHome \
  --input bin \
  --main-class FlyWorldLauncher \
  --main-jar FlyHome.jar \
  --icon img/AppIcon.icns \
  --output .

Ensure that your FlyHome.jar file is correctly created and that the AppIcon.icns is available in the img/ directory.

🕹️ Running the Game

After packaging, the app can be run by double-clicking the FlyHome.app file or using the terminal:

open ./FlyHome.app

📄 License

This project is licensed under the MIT License. Feel free to use and modify as needed.

📧 Contact

For questions or collaboration, feel free to reach out via email or GitHub.

