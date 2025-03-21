package com.example.seniormarket.data

import com.example.seniormarket.R
import com.example.seniormarket.model.Product

class Datasource {
    fun loadProducts() : List<Product>{
        return listOf<Product>(
            Product(R.string.product1, R.drawable.image1, R.string.description1, R.string.price1, R.string.quantity1),
            Product(R.string.product2, R.drawable.image2, R.string.description2, R.string.price2, R.string.quantity2),
            Product(R.string.product3, R.drawable.image3, R.string.description3, R.string.price3, R.string.quantity3),
            Product(R.string.product4, R.drawable.image4, R.string.description4, R.string.price4, R.string.quantity4),
            Product(R.string.product5, R.drawable.image5, R.string.description5, R.string.price5, R.string.quantity5),
            Product(R.string.product6, R.drawable.image6, R.string.description6, R.string.price6, R.string.quantity6),
            Product(R.string.product7, R.drawable.image7, R.string.description7, R.string.price7, R.string.quantity7),
            Product(R.string.product8, R.drawable.image8, R.string.description8, R.string.price8, R.string.quantity8),
            Product(R.string.product9, R.drawable.image9, R.string.description9, R.string.price9, R.string.quantity9),
            Product(R.string.product10, R.drawable.image10, R.string.description10, R.string.price10, R.string.quantity10)
        )
    }
}