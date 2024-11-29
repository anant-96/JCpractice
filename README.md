## NewsAppMVVM
This NewsApp project is implemented using the MVVM architecture pattern alongside Jetpack Compose for a modern, declarative UI approach. It delivers real-time news tailored to a selected country. The application supports category-specific browsing, including filtering by country, news source, and language. Additionally, it features a robust search functionality that enables users to query news articles using keywords.


## Architecture used is MVVM(Model View View Model)
![ViewModel](https://github.com/user-attachments/assets/9b61b122-febe-41df-9b33-1e7e77079373)

## Feature implemented:
- NewsApp
- TopHeadlines of the news
- Country-wise news
- Multiple Languages selection-wise news
- Multiple sources wise news 
- Instant search using Flow operators
  - Debounce
  - Filter
  - DistinctUntilChanged
  - FlatMapLatest
- Offline news
- Pagination
- Unit Test
  - Mockito
  - Turbine 
  - Espresso

## Used Major libraries/frameworks
- **Jetpack compose** for UI
- **Dagger Hilt** for Dependency injection
- **Retrofit** for fetching data
- **RoomDatabase** as the persistent library
- **Pagination** to efficiently load in chunks
- **WorkManager** for fetching news periodically
- **Coil** for image loading


## Naming some more concepts used in the application
- **Debounce operator** - For efficiently searching the news and not making repetitive unnecessary api calls
- **FlatMapLatest** - Making the api call for the latest entered result and ignoring the older ones
- **Filter** - Filtering out the required data from the raw data fetched from the server
- **FlatMapConcat** - Transforms each value of the flow sequentially
- **Map** - Transforms each value of the flow item into another type
- **Coroutine** - For performing asynchronous fetching of data from db or network
- **Flow** - Asynchronous data type used
- **StateFlow** - Obervable asynchronous data structure used


## Detailed summary of the screens implemented
### TopheadlinesScreen
- Shows a generic list of news from the database which is populated by news fetched from the newsApi.
- On clicking on each of the news item, we navigate to a more detailed version of the news on browser.
### NewsSourceScreen
- Shows a list of news sources/news content providers.
- On clicking on each of the source we navigate to their page.
### LanguagesScreen
- Shows a list of languages in which we have the news available.
- On clicking on each of the language we get to see the news in that particular language(**NewsByLanguageScreen**).
### NewsByLanguageScreen
- Shows a list of news in a particular language.
- On clicking on each of the news item, we navigate to a more detailed version of the news on browser.
### CountriesScreen
- Shows a list of the countries whose news we have from the server.
- On clicking on each of the countries, we get to see the news from that country(**NewsByCountryScreen**).
### NewsByCountryScreen
- Shows a list of news of a particular country.
- On clicking on each of the news item, we navigate to a more detailed version of the news on browser.
### InstantSearchScreen 
- It has a search bar to search for news related to a particular keyword.
- When the user is done with typing the keyword, it shows the list of news related to that keyword.
- On clicking on each of the news item, we navigate to a more detailed version of the news on browser.

  
## Attaching the screenshots of the pages for reference
![newsapp screenshots](https://github.com/user-attachments/assets/8fc8352e-543b-42a1-ad56-5c6882d0d857)


## Dependencies used 
- UI(Jetpack Compose)
```
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation (platform("androidx.compose:compose-bom:2023.03.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
```

-  Navigation
```
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
```

- Room Db
```
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")
```

- Pagination
 ```
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.paging:paging-compose:3.2.1")
```

- Work Manager
  ```
    implementation ("androidx.work:work-runtime-ktx:2.9.1")
  ```

- Retrofit
  ```
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
  ```

- Dagger Hilt
  ```
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
  ```


- Coil
  ```
    implementation ("io.coil-kt:coil-compose:2.4.0")
  ```


## The Complete Project Folder Structure

```
|── NewsApplication.kt
├── data
│   ├── api
│   │   └── NetworkService.kt
│   ├── local
│   │   ├── AppDatabase.kt
│   │   ├── AppDatabaseService.kt
│   │   ├── DatabaseService.kt
│   │   ├── dao
│   │   │   └── ArticleDao.kt
│   │   └── entity
│   │       ├── LocalArticle.kt
│   │       └── LocalSource.kt
│   ├── model
│   │   ├── Article.kt
│   │   ├── Country.kt
│   │   ├── HomeItem.kt
│   │   ├── Language.kt
│   │   ├── NewsSourcesResponse.kt
│   │   ├── Source.kt
│   │   └── TopHeadlinesResponse.kt
│   ├── repository
│   │   ├── CountriesRepository.kt
│   │   ├── LanguagesRepository.kt
│   │   ├── NewsSourcesRepository.kt
│   │   ├── OfflineArticleRepository.kt
│   │   ├── TopHeadlinePagingSource.kt
│   │   └── TopHeadlineRepository.kt
│   └── worker
│       └── NewsWorker.kt
├── di
│   ├── module
│   │   ├── ApplicationModule.kt
│   │   └── NetworkHelper.kt
│   └── qualifiers.kt
├── ui
│   ├── HomeScreen.kt
│   ├── base
│   │   ├── CommonUI.kt
│   │   ├── NewsNavigation.kt
│   │   └── UIState.kt
│   ├── countries
│   │   ├── CountriesScreen.kt
│   │   └── CountriesViewModel.kt
│   ├── languages
│   │   ├── LanguagesScreen.kt
│   │   └── LanguagesViewModel.kt
│   ├── main
│   │   └── MainActivity.kt
│   ├── newsSources
│   │   ├── NewsSourcesScreen.kt
│   │   └── NewsSourcesViewModel.kt
│   ├── offlineArticle
│   │   ├── OfflineArticleScreen.kt
│   │   └── OfflineArticleViewModel.kt
│   ├── search
│   │   ├── SearchScreen.kt
│   │   └── SearchViewModel.kt
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   ├── topHeadline
│   │   ├── TopHeadlineScreen.kt
│   │   └── TopHeadlineViewModel.kt
│   └── topHeadlineWithPaging
│       ├── TopHeadlineWithPagingScreen.kt
│       └── TopHeadlineWithPagingViewModel.kt
└── utils
    ├── AppConstant
    ├── DispatcherProvider.kt
    └── TimeUtil
```

## If this project helps you, show love ❤️ by putting a ⭐ on this project ✌️
