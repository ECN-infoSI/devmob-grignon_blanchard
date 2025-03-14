package com.example.seniormarket


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.seniormarket.adapter.ItemAdapter
import com.example.seniormarket.data.Datasource
import com.example.seniormarket.model.Product
import com.example.seniormarket.ui.theme.ProductsTheme


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
                    ProductApp()
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

@Composable
fun ProductApp(){
    ProductList(productList = Datasource().loadProducts())
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
        }
    }

}

@Preview
@Composable
private fun ProductCardPreview() {
    ProductCard(Product(R.string.product1, R.drawable.image1))
}

@Composable
private fun ProductList(
    productList : List<Product>,
    modifier: Modifier = Modifier
){
    LazyColumn{
        items(productList){ product ->
            ProductCard(product)

        }
    }

}