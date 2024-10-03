package me.amitshekhar.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amitshekhar.newsapp.data.model.Language
import me.amitshekhar.newsapp.utils.AppConstant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagesRepository @Inject constructor() {
    fun getLanguages(): Flow<List<Language>> {
        return flow { emit(AppConstant.LANGUAGELIST) }
    }
}
