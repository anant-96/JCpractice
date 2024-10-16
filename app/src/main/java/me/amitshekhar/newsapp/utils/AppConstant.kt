package me.amitshekhar.newsapp.utils

import me.amitshekhar.newsapp.data.model.Country
import me.amitshekhar.newsapp.data.model.Language

object AppConstant {

    const val API_KEY = "9f6482a584804376874b848980b7a044"
    const val COUNTRY = "us"
    const val APP_NAME = "NewsApp"
    const val TOP_HEADLINES = "Top headlines"
    const val OFFLINE_TOP_HEADLINES = "Offline Top headlines"
    const val TOP_HEADLINES_WITH_PAGING = "TopHeadlines With Paging"
    const val LANGUAGES = "Languages"
    const val COUNTRIES = "Countries"
    const val SOURCES = "News Sources"
    const val SEARCH = "News Search"
    const val DEBOUNCE_TIMEOUT = 300L
    const val MIN_SEARCH_CHAR = 3
    const val INITIAL_PAGE = 1
    const val PAGE_SIZE = 10
    const val UNIQUE_WORK_NAME = "newsAppPeriodicWork"
    const val MORNING_UPDATE_TIME = 5

    val COUNTRYLIST = listOf(
        Country("ae", "United Arab Emirates"),
        Country("ar", "Argentina"),
        Country("at", "Austria"),
        Country("au", "Australia"),
        Country("be", "Belgium"),
        Country("bg", "Bulgaria"),
        Country("br", "Brazil"),
        Country("ca", "Canada"),
        Country("ch", "Switzerland"),
        Country("cn", "China"),
        Country("co", "Colombia"),
        Country("cu", "Cuba"),
        Country("cz", "Czech Republic"),
        Country("de", "Germany"),
        Country("eg", "Egypt"),
        Country("fr", "France"),
        Country("gb", "United Kingdom"),
        Country("gr", "Greece"),
        Country("hk", "Hong Kong"),
        Country("hu", "Hungary"),
        Country("id", "Indonesia"),
        Country("ie", "Ireland"),
        Country("il", "Israel"),
        Country("in", "India"),
        Country("it", "Italy"),
        Country("jp", "Japan"),
        Country("kr", "South Korea"),
        Country("lt", "Lithuania"),
        Country("lv", "Latvia"),
        Country("ma", "Morocco"),
        Country("mx", "Mexico"),
        Country("my", "Malaysia"),
        Country("ng", "Nigeria"),
        Country("nl", "Netherlands"),
        Country("no", "Norway"),
        Country("nz", "New Zealand"),
        Country("ph", "Philippines"),
        Country("pl", "Poland"),
        Country("pt", "Portugal"),
        Country("ro", "Romania"),
        Country("rs", "Serbia"),
        Country("ru", "Russia"),
        Country("sa", "Saudi Arabia"),
        Country("se", "Sweden"),
        Country("sg", "Singapore"),
        Country("si", "Slovenia"),
        Country("sk", "Slovakia"),
        Country("th", "Thailand"),
        Country("tr", "Turkey"),
        Country("tw", "Taiwan"),
        Country("ua", "Ukraine"),
        Country("us", "United States"),
        Country("ve", "Venezuela"),
        Country("za", "South Africa")
    )
    val LANGUAGELIST = listOf(
        Language("Arabic", "ar"),
        Language("German", "de"),
        Language("English", "en"),
        Language("Spanish", "es"),
        Language("French", "fr"),
        Language("Hebrew", "he"),
        Language("Italian", "it"),
        Language("Dutch", "nl"),
        Language("Norwegian", "no"),
        Language("Portuguese", "pt"),
        Language("Russian", "ru"),
        Language("Swedish", "sv"),
        Language("Chinese", "zh")
    )
}
