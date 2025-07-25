# ProxyAISuggestions 🍽️🤖

**ProxyAISuggestions** is an Android app that lets users generate UI screens from prompts and manage a meal cart with local storage. It’s built using **Java**, **Room**, and **Dagger Hilt**, and follows the **MVVM architecture** for clean, maintainable code.

---

##Demo of the prompt and how to use it 

https://github.com/user-attachments/assets/730d41eb-de48-4800-9d19-b73b40c795ab

## 🖼️ Screenshots

<table style="width: 100%; border-collapse: collapse;"><tbody><tr><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">meals fragment افضل العروض  </th><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">meals fragment  أجبان قابلة للدهن</th><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">cart السلة</th></tr><tr><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="meals fragment افضل العروض" src="https://github.com/user-attachments/assets/3c59c96f-9733-4be1-a212-ff1512515bd7"></td><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="meals fragment  أجبان قابلة للدهن" src="https://github.com/user-attachments/assets/1d2bba98-92d2-4553-82ad-eb4df49d4054"></td><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="cart السلة" src="https://github.com/user-attachments/assets/82ff853d-4667-4538-a871-1ceea015f91f"></td></tr></tbody></table>


## 🚀 Features

- 🧠 Generate screens from user prompts.
- 📦 Add meals to a local Room database.
- 🛒 View and manage a cart (with quantity and total price).
- 💉 Inject dependencies using Dagger Hilt.
- 🧩 MVVM architecture with LiveData.
- 🔄 Persistent data with Room.
- 🔍 Simple navigation using FragmentManager.

---

## 🏗️ Project Structure

```
ProxyAISuggestions
├── data
│   ├── db                      # Room database & DAO
│   └── models                  # Meal Entity model
│
├── di
│   └── LocalModule.java        # Dagger Hilt local DB provider
│
├── ui
│   ├── CartFragment.java       # Cart screen to view selected meals
│   ├── MealFragment.java       # Meal list screen
│   └── MainActivity.java       # Hosts fragments and manages navigation
│
├── viewmodel
│   ├── CartViewModel.java      # ViewModel to manage cart state
│   └── MealViewModel.java      # ViewModel to fetch meals from DB
```
## proxy ai 
you must ceate account and get the api key then add the plugin to android studio and use the key 

## 🧠 AI Prompt Example

> **Prompt:**  "add room db to the project also add dagger hilt use java , create dao and room in di you will have two entity category and meal each category can have multiple meal so create the relation and in the dao i want to get all the categories and get meals by category id add fallback when the data base created at first time to add a default data, take the data form this screen"
> commit hash 14627647d1376a12b03e9eb8a3b3744681d8406b

> add meal fragment and card view model using proxy ai to generate using prompt "genrate view model based on this repo and this screen image and i want the layout in xml with view binding and use constraint layout"
> commit hash  dec83461645722bc8f1043bfff6bac643bdc5d03

> create cart fragment with adapter and view model using proxy ai
> commit hash 90e39721fec14c2a50fdb4ddfd8e7254f6ec85e3

- The app simulates screen generation.
- Displays meals from DB.
- Each meal has an "Add to Cart" button.
- Cart stores items with count and updates total price.

---


## 🧰 Tech Stack

| Technology    | Purpose                               |
|---------------|---------------------------------------|
| Java          | Main development language             |
| Room          | Local database (meals, cart)          |
| Dagger Hilt   | Dependency Injection                  |
| MVVM          | Architecture                          |
| LiveData      | Reactive UI updates                   |
| ViewBinding   | UI binding without `findViewById`     |
| FragmentManager| Fragment navigation                  |

---

## 🛠️ How to Run

1. Clone this repo:
   ```bash
[   git clone https://github.com/yourusername/ProxyAISuggestions.git
](https://github.com/mohamedebrahem13/ProxyAISuggestions.git)
