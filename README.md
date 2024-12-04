# FinApp

## Project Overview
This project is an Android application developed for a technical assessment. The primary goal is to create an app simulating the transfer of money between different accounts within the app, showcasing my ability to manage authentication, data handling, UI/UX design, and overall Android development.

## Objective
The assessment evaluates the ability to implement core fintech features, manage local data storage, and create an engaging user experience. Key components include:

* **User Authentication**: Basic login functionality using email/password.
* **Account Management**: Displaying user accounts with balances and details.
* **Transfer Interface**: Form for money transfer, validation, and summary.
* **Transaction History**: Storing and displaying past transactions.

## Development Approach
### UI Components Implemented
* **Login Screen**: Allows users to authenticate using email and password.
* **Account List**: Displays a list of mock user accounts with balance details using a RecyclerView.
* **Transfer Form**: Form to input transfer details with validation to prevent exceeding the source account balance.
* **Transaction Summary**: Displays a summary before confirming transactions.
* **Transaction History**: A RecyclerView showing past transactions stored locally.

### Architecture
The app is designed using the **MVVM (Model-View-ViewModel)** architecture pattern to ensure a clear separation of concerns and maintainable code.

### State Management
The app uses **LiveData** to observe and react to data changes, ensuring the UI is updated in real-time based on the ViewModel’s state.

## Data Integration
Mock data is utilized for demonstration purposes. The **Room database** is used for local transaction history storage, providing a realistic setup for data handling.

## Unit test
 Unit tests for critical features and components.

## Libraries and Dependencies
The following libraries are used in this project:
* **Firebase Authentication**: For user login.
* **RecyclerView**: For displaying lists of accounts and transactions.
* **Room**: For local database management of transaction history.
* **LiveData**: For managing real-time data observation.
* **ViewModel**: For handling UI-related data.

## Download APK
You can download the latest APK file from this [Google Drive link](https://drive.google.com/file/d/1vEC5VsVB1UVWMX5EpqwI0z_pOyBN0CdW/view?usp=sharing).

## Firebase Authentication Details

This application uses Firebase Authentication for the login feature. Since the app doesn't include a registration section, two pre-registered accounts are provided for testing:

Email: fintechapp@gmail.com | Password: fintech

Email: davido@gmail.com | Password: 123456

Please use these credentials to log in.

## Installation Instructions
To run this project locally, follow these steps:

1. Clone the repository:
   ```
   git clone https://github.com/OjoLego/finapp.git
   ```
2. Navigate to the project directory:
   ```
   cd FintechApp
   ```
3. Install dependencies:
   ```
   ./gradlew build
   ```
4. Run the application:
   ```
   ./gradlew installDebug
   ```

## Dependencies
Ensure the following are set up in your environment:
* **Android Studio** with Kotlin support.
* **Firebase SDK** for Authentication.
* **Room Database**.
* **ViewModel and LiveData** for UI state management.

## Assumptions and Limitations
* **Mock Data**: The app uses mock data for initial implementation and demonstration purposes. Real backend integration can be added later.
* **Functionality**: The app demonstrates core functionality but lacks certain production-level features like real-time updates.

## Screenshots
Below are some screenshots of the app in action:

![Login Screen](https://github.com/user-attachments/assets/65e8dd8e-04b8-4ef3-8a26-f9f0ea262ccd)

![Account List](https://github.com/user-attachments/assets/1b6a27f7-c440-4426-9a02-78d93a2494f0)

![Transfer Form](https://github.com/user-attachments/assets/b68f6d7e-7f8f-4a2f-9843-15f8b2924aec)

![Transfer Summary](https://github.com/user-attachments/assets/8bb7308b-0af3-498d-8c2b-0db811ded12d)

![Transaction History](https://github.com/user-attachments/assets/14990a15-320d-47f0-9fb3-c9ef848ce7f1)

## Future Enhancements
* Integrate real-time data fetching using a backend API.
* Implement profile management.
* Enhance app responsiveness and optimization for various screen sizes.

