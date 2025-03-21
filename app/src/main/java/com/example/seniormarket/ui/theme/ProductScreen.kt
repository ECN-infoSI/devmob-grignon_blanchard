package com.example.seniormarket.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.seniormarket.R
import com.example.seniormarket.model.Product

@Composable
fun ProductScreen(
    product : Product,
    modifier : Modifier = Modifier,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        // Affichage de la carte du produit
        ProductCard(product)

        // Bouton pour revenir à la page ProductList
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF9AD4E),  // Couleur de fond du bouton
                contentColor = Color(0xFFFFFEF3)     // Couleur du texte
            ),
            onClick = { navController.popBackStack() } // Cette ligne permet de revenir à l'écran précédent
        ) {
            Text(text = "RETOUR AU RAYON")
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var quantity by remember { mutableStateOf("") }  // Variable pour stocker la quantité saisie
    Card(
        colors = CardDefaults.cardColors(
        containerColor = Color(0xFFFCD6A1)),  // Couleur de fond de la Card
        modifier = modifier.padding(8.dp)) {
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Ajout de padding à l'ensemble de la colonne
        ) {

            // Titre centré en haut
            Text(
                text = stringResource(product.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), // Ajout de padding entre le titre et l'image
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center // Centrer le titre
            )

            // Image en dessous du titre
            Image(
                painter = painterResource(product.imageResourceId),
                contentDescription = stringResource(product.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                    .padding(bottom = 8.dp), // Padding pour espacer l'image de la section suivante
                contentScale = ContentScale.Crop
            )

            // Prix et quantité sur la même ligne (utiliser Row)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Price: ${stringResource(product.price)}",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Quantity: ${stringResource(product.quantity)}",
                    modifier = Modifier.weight(1f)
                )
            }

            // Description sous la ligne Prix/Quantité
            Text(
                text = stringResource(product.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Padding autour de la description
            )

            // Les boutons côte à côte (utiliser Row)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Champ de saisie pour la quantité (TextField)
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { newQuantity ->
                        // Assurer que l'utilisateur entre uniquement des chiffres
                        if (newQuantity.all { it.isDigit() }) {
                            quantity = newQuantity
                        }
                    },
                    label = { Text("Enter quantity") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)  // Espacement en bas du champ
                )

                // Bouton Ajouter
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF6921F),  // Couleur de fond du bouton
                        contentColor = Color(0xFF101010)     // Couleur du texte
                    ),
                    onClick = {
                        navController.navigate("product_screen/${product.stringResourceId}")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "AJOUTER")
                }
            }
        }
    }
}


@Preview
@Composable
private fun ProductCardPreview() {

}