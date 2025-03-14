package com.example.seniormarket.data

import com.example.seniormarket.R
import com.example.seniormarket.model.Product

class Datasource {
    fun loadProducts() : List<Product>{
        return listOf<Product>(
            Product(R.string.product1, R.drawable.image1),
            Product(R.string.product2, R.drawable.image2),
            Product(R.string.product3, R.drawable.image3),
            Product(R.string.product4, R.drawable.image4),
            Product(R.string.product5, R.drawable.image5),
            Product(R.string.product6, R.drawable.image6),
            Product(R.string.product7, R.drawable.image7),
            Product(R.string.product8, R.drawable.image8),
            Product(R.string.product9, R.drawable.image9),
            Product(R.string.product10, R.drawable.image10)
        )
    }
}