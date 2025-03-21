package com.example.seniormarket.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product (
    @StringRes val stringResourceId : Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val description: Int,
    @StringRes val price: Int,
    @StringRes val quantity: Int
)