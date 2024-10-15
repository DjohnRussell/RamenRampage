
RamenRampage 🍜

RamenRampage is a noodle tracking app where users can review, log, and celebrate their noodle consumption journey. The app allows users to log different types of noodles, view their noodle consumption history, earn badges for milestones, and see how long they’ve been a member of the noodle community.

Features 📱

	•	Noodle Reviews: Add reviews for different types of noodles including their brand, variety, style, country of origin, and a star rating.
	•	Profile Section: Users can view their profile with a profile picture, username, and membership date.
	•	Photo Log: Users can upload photos of their consumed noodles and view them in a gallery format.
	•	Badge System: Earn badges for different milestones (e.g., first check-in, 50 noodle reviews, etc.) to celebrate your noodle achievements.
	•	Firebase Integration: The app integrates Firebase for user authentication and Firestore for storing user data such as noodle reviews, profile info, and badge achievements.

Installation 🚀

	1.	Clone the repository:

git clone https://github.com/your-username/ramenrampage.git


	2.	Open the project in Android Studio.
	3.	Make sure you have the required dependencies installed. These include:
	•	Firebase Authentication
	•	Firebase Firestore
	•	Glide for image loading
	4.	Set up your Firebase project and update the google-services.json file with your Firebase credentials.
	5.	Build and run the app on an Android emulator or device.

Firebase Setup 🔥

	1.	Set up Firebase Authentication and Firestore:
	•	Enable Firebase Authentication in your Firebase console.
	•	Set up Firestore for storing user reviews, profile info, and badges.
	•	Ensure you’ve correctly configured your Firebase security rules to protect user data.
	2.	Upload Default Badge Images:
	•	Badge images are stored as URLs, so you can either store them in Firebase Storage or host them on a server.
	•	Make sure the URLs point to accessible locations for images like badges and placeholder profile pictures.

Screens 📸

	•	LoginRegistration Screen: Allows users to sign in using their email and password.
 	•	Registration Screen:  Allows users to sign in using their email and password.
	•	Profile Screen: Displays the user’s profile information, including their username, member since date, and profile picture. Users can click the profile picture to change it.
	•	Search Screen: Shows all the noodle  and allows users to search and filter through their reviews. Aswell as add them, upload picture and rate a "check -in"
	•	Badge Screen: Shows earned and unearned badges, motivating users to reach noodle milestones.
	•	Photo Gallery: Displays all uploaded photos of consumed noodles in rows and columns.

Badges 🏅

The app includes a variety of badges to reward users for their achievements:

Badge(
    imageUrl = "https://example.com/first-checkin-badge.png",
    name = "First Check-In",
    description = "Earned after your first noodle check-in",
    achieved = false
),
Badge(
    imageUrl = "https://example.com/noodle-master-badge.png",
    name = "Noodle Master",
    description = "Awarded after 50 noodle reviews",
    achieved = false
),
Badge(
    imageUrl = "https://example.com/noodle-enthusiast-badge.png",
    name = "Noodle Enthusiast",
    description = "Logged 100 noodle reviews",
    achieved = false
),
Badge(
    imageUrl = "https://example.com/photo-log-badge.png",
    name = "Photo Log Champion",
    description = "Uploaded 10 photos of your noodle meals",
    achieved = false
)

You can extend the badge system by adding more milestones based on the number of noodle reviews, types of noodles consumed, or photos uploaded.

Technologies Used 🛠

	•	Kotlin: The primary programming language used in the app.
	•	Jetpack Compose: For building the user interface.
	•	Firebase Authentication: For user sign-in and registration.
	•	Firebase Firestore: For storing user data such as reviews, badges, and profile information.
	•	Glide: For loading images efficiently.

Future Enhancements ✨

	•	Push Notifications: Remind users to check in and review noodles periodically.
	•	Noodle Leaderboard: Compare noodle consumption and badges with other users.
	•	Social Sharing: Allow users to share their noodle achievements and reviews on social media.
	•	More Badges: Expand the badge system to include more milestones, including types of noodles consumed (e.g., ramen, soba, pho, etc.).

Contributing 🤝

Contributions are welcome! Feel free to submit a pull request or open an issue for any improvements or bug fixes.

## Author

Created by Daniel John Russell

Feel free to reach out for questions or collaboration opportunities!
You can find me on
LinkedIn : (https://www.linkedin.com/in/daniel-john-russell-02a1a513a/) or 
contact me at: russelldanieljohn@gmail.com

License 📄

This project is licensed under the MIT License - see the LICENSE file for details.

This README provides an overview of your project, details about how to install and use it, and information about the app’s features and future plans. You can customize this further based on any additional features or changes you introduce!
