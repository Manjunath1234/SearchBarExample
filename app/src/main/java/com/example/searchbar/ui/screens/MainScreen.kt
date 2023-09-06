package com.example.searchbar.ui.screens


import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.searchbar.MainViewModel
import com.example.searchbar.ui.SearchWidgetState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(mainViewModel: MainViewModel){
    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState
    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChanged = {
                                mainViewModel.updateSearchTextState(it)
                },
                onCloseClicked = {
                    mainViewModel.updateSearchTextState("")
                    mainViewModel.updateSearchWidgetState(SearchWidgetState.CLOSE)
                },
                onSearchClicked = {
                                  Log.d("Searched Text",it)
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(SearchWidgetState.OPEN)
                }
            )
        }
    ) {

    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState:String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered:()->Unit
){
   when(searchWidgetState){
       SearchWidgetState.CLOSE -> {
           DefaultAppBar(
               onSearchClicked = onSearchTriggered
           )
       }
       SearchWidgetState.OPEN -> {
           SearchAppBar(
               text = searchTextState,
               onTextChanged = onTextChanged,
               onCloseClicked = onCloseClicked,
               onSearchClicked = onSearchClicked
           )
       }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChanged:(String)->Unit,
    onCloseClicked:()->Unit,
    onSearchClicked:(String)->Unit
){
   Surface(
       modifier = Modifier
           .fillMaxWidth()
           .height(56.dp),
       shadowElevation = 2.dp,
       color = MaterialTheme.colorScheme.primary
   ) {
       TextField(
           modifier = Modifier.fillMaxWidth(),
           value = text,
           onValueChange ={
               onTextChanged(it)
           } ,
           placeholder = {
               Text(
                   modifier = Modifier.alpha(0.5f),
                   text = "Search here..",
                   color = Color.White
               )
           },
           textStyle = TextStyle(
               fontSize =  MaterialTheme.typography.titleSmall.fontSize
           ),
           singleLine = true,
           leadingIcon = {
               IconButton(
                   modifier = Modifier.alpha(0.6f),
                   onClick = {},

                   ) {
                   Icon(imageVector = Icons.Default.Search,
                       contentDescription ="Search Icon",
                       tint = Color.White
                   )
               }
           },
       trailingIcon =  {
           IconButton(
               onClick = {
                         if (text.isNotEmpty()){
                             onTextChanged("")
                         }else{
                             onCloseClicked()
                         }
               },

               ) {
               Icon(imageVector = Icons.Default.Close,
                   contentDescription ="Close Icon",
                   tint = Color.White
               )
           }
         },
           keyboardOptions = KeyboardOptions(
               imeAction = ImeAction.Search
           ),
           keyboardActions = KeyboardActions(
               onSearch = { onSearchClicked(text) }
           ),
           colors = TextFieldDefaults.textFieldColors(
               containerColor = Color.Transparent,
               cursorColor = Color.White.copy(alpha = 0.5f)
           )
       )
   }
}

@Preview
@Composable
fun SearchAppBarPreview(){
    SearchAppBar(
        text = "Some random text",
        onTextChanged = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
   TopAppBar(
       colors = TopAppBarDefaults.mediumTopAppBarColors(
           containerColor = MaterialTheme.colorScheme.primary
       ),
       title = {
           Text("Home",
           color = Color.White)
       },
       actions = {
           IconButton(onClick = { onSearchClicked()}) {
               Icon(
                   imageVector = Icons.Default.Search,
                   contentDescription = "Search Icon",
                   tint = Color.White
               )
           }
       }
   )
}

@Preview
@Composable
fun DefaultAppBarPreview(){
    DefaultAppBar(onSearchClicked = {})
}
