package com.silaeva.alfa.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.silaeva.alfa.R
import com.silaeva.alfa.presentation.theme.AlfaTheme
import com.silaeva.alfa.presentation.ui.history.HistoryScreen
import com.silaeva.alfa.presentation.ui.info.InfoScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlfaTheme {

                val chosenScreen = remember { mutableStateOf(ScreenRoute.INFO) }

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier,
                            containerColor = Color.DarkGray,
                            contentColor = Color.White,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .clickable { chosenScreen.value = ScreenRoute.INFO },
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Search",
                                        tint = if (chosenScreen.value == ScreenRoute.INFO) Color.Yellow else Color.White
                                    )
                                    Text(
                                        text = "Search",
                                        color = if (chosenScreen.value == ScreenRoute.INFO) Color.Yellow else Color.White
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .clickable { chosenScreen.value = ScreenRoute.HISTORY },
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_storage),
                                        contentDescription = "History",
                                        tint = if (chosenScreen.value == ScreenRoute.HISTORY) Color.Yellow else Color.White
                                    )
                                    Text(
                                        text = "History",
                                        color = if (chosenScreen.value == ScreenRoute.HISTORY) Color.Yellow else Color.White
                                    )
                                }
                            }
                        }
                    },
                    content = {
                        when (chosenScreen.value) {
                            ScreenRoute.INFO -> InfoScreen(
                                paddingValues = it,
                                onErrorShow = {
                                    Toast.makeText(this, "Wrong BIN", Toast.LENGTH_LONG).show()
                                }
                            )
                            ScreenRoute.HISTORY -> HistoryScreen(paddingValues = it)
                        }
                    }
                )
            }
        }
    }

    enum class ScreenRoute {
        INFO,
        HISTORY
    }
}