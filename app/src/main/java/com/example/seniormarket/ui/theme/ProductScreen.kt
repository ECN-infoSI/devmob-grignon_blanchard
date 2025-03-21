package com.example.seniormarket.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.seniormarket.R
import com.example.seniormarket.model.Product

@Composable
fun ProductScreen(
    modifier : Modifier = Modifier,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        // Affichage de la carte du produit
        ProductCard(Product(R.string.product1, R.drawable.image1))

        // Bouton pour revenir à la page ProductList
        Button(
            onClick = { navController.popBackStack() } // Cette ligne permet de revenir à l'écran précédent
        ) {
            Text(text = "Retour")
        }
    }
}

@Composable
fun ProductCard(
    product : Product,
    modifier : Modifier = Modifier
){
    Card(modifier = modifier.padding(8.dp), /*elevation = 4.dp*/){
        Column {
            Text(
                text = stringResource(product.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(product.imageResourceId),
                contentDescription = stringResource(product.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Prix",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "kilo",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "description",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick= { /*..*/}
            ){
                Text(text = "1")
            }
            Button(
                onClick= { /*..*/}
                /*colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary*/

            ) {
                Text(text = "Afficher")
            }


        }
    }

}

@Preview
@Composable
private fun ProductCardPreview() {
    ProductCard(Product(R.string.product1, R.drawable.image1))
}