# 🗞️ News Inshort

**News Inshort** is a modern Android application built using **Jetpack Compose** and **Clean Architecture**.  
It provides users with a concise list of news articles fetched from a remote API, allowing them to explore detailed content with a smooth and responsive UI.

The project follows the **Model–View–Intent (MVI)** architectural pattern for predictable state management and improved code scalability.

---

## ✨ Features

- 📰 Fetches the latest news articles from a remote API  
- 📄 Displays a detailed view of each article upon selection  
- 💡 Built with Jetpack Compose for a fully declarative and reactive UI  
- 🧱 Implements Clean Architecture and MVI for a clear separation of concerns  
- ⚙️ Uses Dependency Injection (Hilt) for modular and testable code  
- 🖼️ Loads article images efficiently using Coil  

---

## 🧰 Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** MVI + Clean Architecture  
- **Dependency Injection:** Hilt  
- **Networking:** Retrofit  
- **Image Loading:** Coil  
- **Asynchronous Programming:** Kotlin Coroutines  

---

## 🏗️ Architecture Overview

The app is organized according to **Clean Architecture** principles, separating concerns into distinct layers:

- **Data Layer:** Handles API interactions, DTOs, and mappers.  
- **Domain Layer:** Contains business logic and use cases for retrieving and transforming data.  
- **Presentation Layer:** Manages UI state, user intents, and composable screens through ViewModels (MVI).  

This layered approach ensures maintainability, scalability, and testability as the project grows.

---

## 🧭 Navigation Flow

1. **News List Screen:** Fetches and displays a list of articles.  
2. **Details Screen:** Shows full details (title, content, image) of the selected article.  

Navigation between screens is fully managed within the Compose ecosystem for a seamless user experience.

---

## 📄 License

This project is intended for educational and portfolio purposes.  
You are free to explore, modify, and learn from the codebase.
