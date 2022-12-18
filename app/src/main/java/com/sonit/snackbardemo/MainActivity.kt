package com.sonit.snackbardemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sonit.snackbardemo.ui.theme.SnackBarDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarDemoTheme {
                    DisplaySnackBar()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DisplaySnackBar() {

    val scaffoldState:ScaffoldState = rememberScaffoldState()
    val coroutineScope:CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState){

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = "This is a SnackBar",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                    when(snackbarResult) {
                        SnackbarResult.ActionPerformed -> Log.i("INFO", "Action Label Clicked  ")
                        SnackbarResult.Dismissed -> Log.i("INFO","SnackBar Dismissed")
                    }
                }
            }) {
                Text(text = "Display SnackBar")
            }
        }
    }

}


@Preview
@Composable
fun ComposablePreview() {
    DisplaySnackBar()
}
