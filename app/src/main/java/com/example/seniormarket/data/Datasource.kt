package com.example.seniormarket.data

import com.example.seniormarket.R
import com.example.seniormarket.model.Product

class Datasource {
    fun loadProducts() : List<Product>{
        return listOf<Product>(
            Product(R.string.product1),
            Product(R.string.product2),
            Product(R.string.product3),
            Product(R.string.product4),
            Product(R.string.product5),
            Product(R.string.product6),
            Product(R.string.product7),
            Product(R.string.product8),
            Product(R.string.product9),
            Product(R.string.product10),
        )

    }

}