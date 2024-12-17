[![Build Android Project](https://github.com/ravishankarahirwar/AndroidAppArchitecture/actions/workflows/main.yml/badge.svg)](https://github.com/ravishankarahirwar/AndroidAppArchitecture/actions/workflows/main.yml)

# AndroidAppArchitecture
Android MVVM Architecture Code Sample
This repository contains a sample Android project demonstrating the MVVM (Model-View-ViewModel) architecture, implemented with Clean Architecture principles. The sample project shows how to structure an Android application using a Domain Layer, Use Cases, Repository Pattern, and how to follow the best practices for maintainable, testable, and scalable Android development.

Features
Implements MVVM Architecture with a clear separation of concerns.
Demonstrates the usage of Clean Architecture with distinct layers (Presentation, Domain, Data).
Use Case driven approach, encapsulating business logic in a dedicated layer.
Sample app that fetches data from a REST API and displays it in a RecyclerView.
Written in Kotlin with Android Jetpack components.
Project Structure
The project is organized in the following layers:

Data Layer:

Handles data operations (API calls, local data storage).
Contains the implementation of the repository.
Domain Layer:

Encapsulates business logic with Use Cases.
Use Case classes execute specific actions on data and return results to the ViewModel.
Presentation Layer:

Contains the ViewModel and UI components (e.g., Activities, Fragments).
ViewModel interacts with Use Cases and updates the UI with results.
Tech Stack
Kotlin – Programming language for Android development.
MVVM Architecture – Model-View-ViewModel pattern.
Clean Architecture – A layered architecture pattern.
Retrofit – For network API calls.
Hilt – Dependency Injection (DI) library for Android.
LiveData – For observing data and UI updates in the ViewModel.
RecyclerView – To display the list of items fetched from the API.
Installation
Follow these steps to get the project running on your local machine:
Open the Project
Open Android Studio.
Click File > Open and select the cloned repository folder.
Sync Dependencies
Once the project is open, Android Studio will automatically sync the project with the Gradle files. If it doesn’t, you can manually sync by clicking File > Sync Project with Gradle Files.

Run the Application
To run the application:

Connect an Android device or start an Android emulator.
Click the Run button in Android Studio or press Shift + F10.
Architecture Overview
Here’s a quick overview of how the layers interact:

View:

The UI layer (e.g., MainActivity) that observes the LiveData in the ViewModel to update the UI when data changes.
ViewModel:

Contains the UI-related data and logic. It fetches data from the Use Case (business logic layer) and updates the View.
Use Case:

Contains the business logic. Use cases are used by the ViewModel to perform actions like fetching data from the repository.
Repository:

The data layer where the logic for fetching data is handled, either from a local database, a remote API, or a combination of both.
Example Use Case
Here's an example of how the ViewModel interacts with the Use Case:
class ItemViewModel(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {

    fun getItems() = liveData {
        try {
            val items = getItemsUseCase.execute()
            emit(items)
        } catch (exception: Exception) {
            emit(emptyList<Item>())
        }
    }
}
In the above code:

ItemViewModel fetches the list of items by calling the Use Case (GetItemsUseCase).
The ViewModel exposes the data as LiveData which the Activity or Fragment observes.
Contributing
Contributions are welcome! If you find any bugs or want to improve the project, feel free to fork the repository and create a pull request.

Steps to Contribute:
Fork this repository.
1. Clone your forked repository locally:
2. Create a new branch for your changes:
3. Make your changes, then commit them:
4. Push your changes to your fork:
5. Open a pull request on GitHub.



This is a public repository to dimostrate, how we can make a testable app architecture with clean architecture
