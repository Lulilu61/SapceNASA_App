package fr.sdv.b32526.spacenasa_app.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.sdv.b32526.spacenasa_app.data.AstronautEntity
import fr.sdv.b32526.spacenasa_app.presentation.viewmodel.SpaceViewModel

@Composable
fun SpaceScreen(
    modifier: Modifier = Modifier,
    spaceViewModel: SpaceViewModel = viewModel()
) {
    val state by spaceViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🚀 Mission : Qui est dans l'Espace ?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 3. Gestion des 3 états de l'interface
        if (state == null) {
            // ÉTAT 1 : Chargement
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state?.status != "success") {
            // ÉTAT 2 : Erreur
            Text(text = "Impossible de charger les données.", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { spaceViewModel.loadAstronauts() }) {
                Text("Réessayer")
            }
        } else {
            // ÉTAT 3 : Succès

            // Affichage du nombre total d'astronautes là-haut
            Text(
                text = "Actuellement là-haut : ${state?.totalAstronauts} humains",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // La liste défilante
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state?.astronautsList ?: emptyList()) { astronaut ->
                    AstronautRow(astronaut = astronaut)
                }
            }
        }
    }
}

@Composable
fun AstronautRow(astronaut: AstronautEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = astronaut.fullName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "À bord de : ${astronaut.spaceCraft}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}