# 🍽️ Restaurants App

A modern Android application built with Jetpack Compose that allows users to browse and explore restaurants with a beautiful, intuitive UI.

## ✨ Features

- **Restaurant List**: Browse restaurants with beautiful card-based layout
- **Restaurant Details**: Detailed view with hero images and collapsible app bar
- **Modern UI**: Material 3 Design with smooth animations and transitions
- **Responsive Design**: Adaptive layouts for different screen sizes
- **State Management**: Robust state handling with loading, error, and success states

## 🏗️ Architecture

This app follows modern Android development best practices:

- **MVVM Architecture**: Clean separation of concerns with ViewModels
- **Jetpack Compose**: Declarative UI framework for modern Android
- **State Management**: Unidirectional data flow with UI states
- **Material 3**: Latest Material Design components and theming

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Design System**: Material 3
- **Build System**: Gradle with Kotlin DSL
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

## 📱 Screenshots

### Restaurant List Screen

- Card-based layout with restaurant images
- Rating and price information
- Cuisine type chips

### Restaurant Details Screen

- Detailed restaurant information
- Smooth scroll animations
- Rounded content design

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 11 or later
- Android SDK with API 34

### Installation

1. Clone the repository:

```bash
git clone <repository-url>
cd Restaurants
```

1. Open the project in Android Studio

1. Wait for Gradle sync to complete

1. Run the app on an emulator or physical device

### Building the App

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

## 📁 Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/io/mohammedalaamorsi/restaurants/
│       │   ├── data/                    # Data models and repositories
│       │   ├── domain/                  # Business logic and use cases
│       │   └── presentation/            # UI layer
│       │       ├── restaurants_list/    # Restaurant list screen
│       │       ├── restaurant_details/  # Restaurant details screen
│       │       ├── states/             # UI state definitions
│       │       ├── components/         # Reusable UI components
│       │       └── theme/              # App theming
│       └── res/                        # Resources (strings, colors, etc.)
├── build.gradle.kts                    # App-level build configuration
└── proguard-rules.pro                  # ProGuard configuration
```

## 🎨 UI Components

### RestaurantsListScreen

- Displays a list of restaurants in card format
- Shows loading states and error handling
- Smooth animations and Material 3 ripple effects

### RestaurantDetailsScreen

- Rounded content that adapts to scroll position
- Detailed restaurant information display
- Back navigation with proper state management

### Custom Components

- Restaurant cards with rounded corners
- Rating displays with stars
- Cuisine type chips

## 🔧 Configuration

### Gradle Configuration

The app uses Gradle with Kotlin DSL for build configuration. Key dependencies include:

- Jetpack Compose BOM for version alignment
- Material 3 for design components
- Navigation Compose for screen navigation

### ProGuard

ProGuard rules are configured for release builds to optimize app size and performance.

## 📝 Code Style

This project follows:

- Kotlin coding conventions
- Material 3 design guidelines
- Jetpack Compose best practices
- Clean Architecture principles

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Material Design team for the beautiful design system
- Jetpack Compose team for the modern UI framework
- Android development community for best practices and guidance

---

Made with ❤️ using Jetpack Compose
