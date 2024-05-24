package com.ahuaman.navigationsafecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ahuaman.navigationsafecompose.ui.theme.NavigationSafeComposeTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSafeComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val controller = rememberNavController()

                    NavHost(navController = controller, startDestination = ScreenA) {
                        composable<ScreenA> {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(innerPadding),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Screen A")
                                Button(onClick = {controller.navigate(ScreenB(name = "PaparazziTeam")) }) {
                                    Text(text = "Navigate to Screen B")
                                }
                            }
                        }

                        composable<ScreenB>{
                            val arg = it.toRoute<ScreenB>()
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "I'm already in Screen B - ${arg.name}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(val name: String)



