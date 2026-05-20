package fr.sdv.b32526.spacenasa_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import fr.sdv.b32526.spacenasa_app.presentation.ui.SpaceScreen
import fr.sdv.b32526.spacenasa_app.ui.theme.SpaceNASA_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceNASA_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}