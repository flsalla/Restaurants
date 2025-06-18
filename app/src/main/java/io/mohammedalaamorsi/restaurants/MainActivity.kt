package io.mohammedalaamorsi.restaurants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.mohammedalaamorsi.restaurants.navigation.RootNavHost
import io.mohammedalaamorsi.restaurants.presentation.theme.RestaurantsTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KoinAndroidContext {
                        RootNavHost(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestaurantsTheme {
        RootNavHost()
    }
}
