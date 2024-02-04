package com.example.animationviewpager.utils

import com.example.animationviewpager.R
import com.example.animationviewpager.model.LocationModel

object Instance {
    val locationModels = listOf(
        LocationModel(
            title = "Amazon",
            description = "Colombia, Brazil, and Bolivia, the Peruvian Amazon accounts for more than 60 percent of the country",
            image = R.drawable.amazon
        ),
        LocationModel(
            title = "China",
            description = "One of China\\'s largest bamboo forests, located in Yibin City of Sichuan Province. It\\'s also known as the Shunan Bamboo Sea",
            image = R.drawable.chaina
        ),
        LocationModel(
            title ="Africa",
            description = "Visit Lesotho and you will know why this tiny country is called \"Kingdom in the Sky\". Here are the best things to do in Lesotho, Africa's mountain Kingdom.",
            image = R.drawable.africa
        ),
    )
}