# HitShop

## English

### Overview
**HitShop** is a modern e-commerce mobile application inspired by platforms like **Trendyol**.  
It allows users to browse products, search, view details, and add items to their favorites — all within a sleek, user-friendly interface.  
The app is built using **Jetpack Compose** with **MVVM Clean Architecture** and incorporates best Android development practices.

---

### Features
- Product listing and detail pages
- Add/remove favorites
- Real-time product search
- Full light & dark mode support
- Local data persistence with Room
- Clean architecture with ViewModel, Repository, and UseCase layers
- Dependency injection with **Dagger-Hilt**

---

### Features
| Category | Technologies |
|-----------|--------------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM Clean Architecture |
| Dependency Injection | Dagger-Hilt |
| Async | Coroutines, LiveData |
| State Management | State & MutableState |
| Database | Room |
| English And Turkish language support |

---

### Screenshots

####  Light Mode
<p align="center">
  <img src="screenshots/1.png" width="22%" />
  <img src="screenshots/2.png" width="22%" />
  <img src="screenshots/3.png" width="22%" />
  <img src="screenshots/4.png" width="22%" />
</p>

####  Dark Mode
<p align="center">
  <img src="screenshots/5.png" width="22%" />
  <img src="screenshots/6.png" width="22%" />
  <img src="screenshots/7.png" width="22%" />
  <img src="screenshots/8.png" width="22%" />
</p>

---

### Architecture
The project follows **MVVM Clean Architecture**, consisting of:
- **UI Layer (Compose)** — reactive UI using State and LiveData
- **ViewModels** — viewmodel for every screen
- **Data Layer** — repositories, datasources,entity

This structure improves testability, scalability, and maintainability.

---

###  Installation
```bash
git clone https://github.com/eraykstrl/HitShop-ECommerce.git
