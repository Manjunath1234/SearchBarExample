package com.example.searchbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.searchbar.ui.screens.MainScreen
import com.example.searchbar.ui.theme.SearchBarTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel:MainViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {
                   MainScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}

