# BookStore

Bookstore is a Jetpack Compose Android app for managing and browsing a personal bookstore.

## 🚀 Features

- 📘 **Book List**  
  Displays books in a card layout with image, title, author, description using ConstraintLayout+LazyColumn.

- 🧭 **Navigation**  
  Using Navigation to change screens and pass data.

- 💾 **Data Persistence**  
  Book data is stored locally as JSON, supporting manual edits and automatic rendering of updates.

- 🖼 **Image Handling**  
  Supports image selection and display via `AsyncImage` from Jetpack Compose and `Uri` storage.

- 🗑 **Swipe-to-Delete with Confirmation**  
  Swipe-to-delete functionality using `DismissState`, with confirmation dialog before final deletion.

- 🍞 **Toast Feedback**  
  Uses Android Toasts to inform users about errors.

## Key Learning Points
- **Serialization**: Saving data locally as Json, also adding UUID to data class for easier referencing
- **Navigation**: Using NavGraphBuilder, NavHost, NavController to navigate and pass data between screens
- **Logging & Debugging**: Learned importance of identifying crashes, tracking unexpected behavior, diagnosing state issues through Logcat
- Understood differences between **Dependencies**: External libraries; **Plugins**: Gradle features/extensions; **libs.versions.toml**: Central config file