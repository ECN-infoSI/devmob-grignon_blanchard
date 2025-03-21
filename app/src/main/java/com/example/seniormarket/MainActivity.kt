package com.example.seniormarket


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seniormarket.data.Datasource
import com.example.seniormarket.model.Product
import com.example.seniormarket.ui.theme.ProductsTheme
import androidx.compose.material3.Button
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seniormarket.ui.theme.ProductScreen


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductApp(

                    )
                }
            }
        }
        /*enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize data.
        val myDataset = Datasource().loadProducts()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)*/

    }
}

enum class ProductsScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Product1(title = R.string.product1),
    Product2(title = R.string.product2),

}

@Composable
fun ProductApp(
    //viewModel: OrderViewModel,
    navController: NavHostController = rememberNavController()
){


    //pour permettre la navigation
    //val backStackEntry by navController.currentBackStackEntryAsState()
    /*val currentScreen = ProductsApp.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )*/

    Scaffold(

    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = ProductsScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ProductsScreen.Start.name){
                ProductList(
                    navController = navController,
                    productList = Datasource().loadProducts())

            }
            // Ecran de détail du produit avec les arguments stringResourceId et imageResourceId
            composable(route = "product_screen/{stringResourceId}/{imageResourceId}") { backStackEntry ->
                // Récupérer les arguments passés dans la route
                val stringResourceId = backStackEntry.arguments?.getString("stringResourceId")?.toInt() ?: -1
                val imageResourceId = backStackEntry.arguments?.getString("imageResourceId")?.toInt() ?: -1

                // Trouver le produit correspondant
                val product = Product(stringResourceId, imageResourceId)

                // Afficher le produit spécifique
                ProductScreen(
                    modifier = Modifier.fillMaxSize(),
                    product = product,
                    navController = navController
                )
            }

        }

    }
}

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()

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
            Button(
                onClick= { navController.navigate("product_screen/${product.stringResourceId}/${product.imageResourceId}")}
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
    ProductCard(Product(R.string.product1, R.drawable.image2))
}

@Composable
private fun ProductList(
    navController: NavHostController,
    productList : List<Product>,
    modifier: Modifier = Modifier
){
    LazyColumn{
        items(productList){ product ->
            ProductCard(product, modifier, navController)

        }
    }

}