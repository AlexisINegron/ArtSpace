# ArtSpace

An Android application built with Kotlin and Jetpack Compose that lets users browse a curated gallery of famous artworks, viewing each piece alongside its title, artist, and year.

## Overview

ArtSpace displays a rotating gallery of artwork. Each entry includes the artwork image, title, artist name, and year of creation. Tapping any artwork opens a dedicated artist page with the artist's portrait, nationality, lifespan, and full biography. Users navigate between pieces using Previous and Next controls on the Home screen.

## Features

- **Artwork gallery**: Five artworks are included, each with its image, title, year, and a detailed artist biography (e.g., Vincent van Gogh, Leonardo da Vinci, William Bouguereau, Alfred Stevens, Karl Wiener).
- **Built with Jetpack Compose**: The UI is built entirely with declarative Compose components (`Scaffold`, `Column`, `Row`, `Image`, `Button`) rather than traditional XML layouts.
- **Navigation**: Uses Jetpack Navigation Compose (`NavHost`, `NavController`) to move between the Home screen and a fully implemented Artist detail screen.
- **Structured data model**: Artwork data is defined through a `data class Art` and stored in a central `DataSource` object, keeping content separate from UI logic and making it easy to add new artworks.
- **String and drawable resources**: All artwork text and images are stored as Android resources (`strings.xml`, `drawable/`), following Android best practices for localization and asset management.

## Project Structure

```
app/
├── src/main/java/com/example/artspace/
│   ├── MainActivity.kt         # App entry point, navigation host, Home/Artist composables
│   ├── AppNavigation.kt        # Screen route definitions
│   ├── model/Art.kt            # Data class representing a single artwork
│   └── data/DataSource.kt      # In-app dataset of artworks
├── src/main/res/
│   ├── drawable/                # Artwork and artist images
│   └── values/strings.xml       # Artwork titles, descriptions, and artist bios
```

## How to Run

1. Clone the repository and open the project root in Android Studio (the folder containing `settings.gradle.kts`).
2. Let Gradle sync and download dependencies.
3. Run the app on an emulator or physical device (minimum SDK as defined in `app/build.gradle.kts`).

## Current Status

The app is fully functional. Users can browse the gallery using Previous/Next navigation on the Home screen, and tapping any artwork opens a complete Artist detail page displaying the artist's portrait, name, nationality and lifespan, and full biography.

## What I Learned

This project was my introduction to Jetpack Compose and declarative UI design in Android, as well as structuring an app around a clean data model instead of hardcoding content directly into the UI. It also gave me hands-on experience with Compose Navigation for moving between screens.
