# Android Developer Assessment Task

## Project Overview
This project is an Android application developed for a technical assessment. The primary goal is to create an app simulating the transfer of money between different accounts within the app, showcasing the developer's ability to manage authentication, data handling, UI/UX design, and overall Android development.

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

## Libraries and Dependencies
The following libraries are used in this project:
* **Firebase Authentication**: For user login.
* **RecyclerView**: For displaying lists of accounts and transactions.
* **Room**: For local database management of transaction history.
* **LiveData**: For managing real-time data observation.
* **ViewModel**: For handling UI-related data.

## Installation Instructions
To run this project locally, follow these steps:

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/FintechApp.git
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

![Login Screen](https://github.com/user-attachments/assets/your-screenshot-link)

![Account List](https://github.com/user-attachments/assets/your-screenshot-link)

![Transfer Form](https://github.com/user-attachments/assets/your-screenshot-link)

![Transaction History](https://github.com/user-attachments/assets/your-screenshot-link)

## Future Enhancements
* Integrate real-time data fetching using a backend API.
* Implement user authentication and profile management.
* Enhance app responsiveness and optimization for various screen sizes.
* Add unit tests for critical features and components.

