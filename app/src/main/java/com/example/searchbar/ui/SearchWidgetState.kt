package com.example.searchbar.ui

sealed class SearchWidgetState {
    object OPEN:SearchWidgetState()
    object CLOSE:SearchWidgetState()
}