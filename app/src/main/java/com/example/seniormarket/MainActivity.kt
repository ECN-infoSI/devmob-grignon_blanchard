package com.example.seniormarket


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
    Product(title = R.string.product1),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = { /* TODO : Go to main menu */ }) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Go back home button"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO : Go to previous carts menu */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.List,
                    contentDescription = "Consult previous carts button"
                )
            }
            IconButton(onClick = { /* TODO : Go to cart menu */ }) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "Consult cart button"
                )
            }
        }
    )
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
       topBar = { NavBar() }
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
            composable(route = ProductsScreen.Product.name){
                val context = LocalContext.current
                ProductScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController,
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
                onClick= { navController.navigate(ProductsScreen.Product.name)}
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
    LazyColumn (
        modifier = modifier
    ){
        items(productList){ product ->
            ProductCard(product, modifier, navController)
        }
    }

}