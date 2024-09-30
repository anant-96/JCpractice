package me.amitshekhar.newsapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import me.amitshekhar.newsapp.ui.base.NewsNavHost
import me.amitshekhar.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                NewsNavHost()
            }
        }
    }
}
