# IqMafiaApp

**IqMafiaApp** is a mobile companion app designed to streamline the management of **Mafia (Sports Mafia)** games. It lets users create and manage game sessions, assign roles to players, track player statistics, and advance through the **day/night** phases of the game. The app is built with **Kotlin Multiplatform Mobile (KMM)** and **Jetpack Compose**, enabling a shared codebase for both Android and iOS.

## Key Features

- **Cross-Platform (KMM):** Shared Kotlin codebase for Android and iOS to simplify development and reduce duplication.  
- **Declarative UI (Jetpack Compose):** Uses Jetpack Compose for modern, efficient UI development on both platforms.  
- **Slots Management:** Create and manage multiple game sessions. The “New Slot” screen allows starting a new game or selecting from saved slots.  
- **Player Statistics:** Maintain a list of players for each game, including custom **Rate**, **Dops** (game-specific metric), and **Comments** fields. Supports editing and saving player data.  
- **Role Cards:** View and reveal player role cards (e.g., mafia, sheriff) with a stylized full‑screen interface.  
- **Day/Night Phases:** Navigate through the game’s day and night cycles to help moderators announce phase changes and events.  
- **Score Tracking:** Keep track of game results and player scores in the **Score** section for easy post‑game review.  
- **Offline Data Storage:** All game data is stored locally on the device, allowing full functionality without network access.

## Screenshots
![new slot](https://github.com/user-attachments/assets/9cd19f2f-e571-45c2-bdaa-7d2897d46814)

*Main Slots view with “New Slot” prompt and “Game” button. Bottom nav bar for quick access.*


![score](https://github.com/user-attachments/assets/a1bdd8cf-b279-4840-baf2-1d678082ab88)

 *Player list showing editable Rate, Dops, and Comment fields. Color accents indicate role/team. “Save” button persists changes.*


![cards](https://github.com/user-attachments/assets/4b5ba56e-1395-4b1a-9759-3da95018c4dd)

*Full‑screen role reveal—here shown as the big red “S” for Sheriff. Tap to next.*


**Tech Stack**
- Kotlin Multiplatform Mobile (KMM): Enables writing shared business logic for Android and iOS from a single codebase
- Jetpack Compose: Modern declarative UI toolkit for Android (and Compose Multiplatform for iOS)
- Android & iOS: Native platform projects (androidApp and iosApp) that host the shared modules.
- Shared Modules: Includes shared for common logic and shared-ui for Compose-based UI components, organized in a multimodule Gradle setup.
- Kotlin Coroutines: For asynchronous tasks and managing concurrency.
- Local Storage: Persisting game data on-device (e.g., using Kotlin serialization or a multiplatform database library).
- Material Design: UI styling follows Material Design principles for a clean, intuitive interface.
