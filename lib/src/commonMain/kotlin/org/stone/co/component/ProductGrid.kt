package org.stone.co.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.stone.co.domain.Product
import org.stone.co.navigation.GridNavProvider
import org.stone.co.ui.productsMock

@Composable
fun ProductsListComponent(modifier: Modifier = Modifier, navigate: (GridNavProvider) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductGrid(productsMock, navigate = navigate)
    }
}

@Composable
private fun ProductGrid(products: List<Product>, modifier: Modifier = Modifier, navigate: (GridNavProvider) -> Unit) {
    val rows = (products.size + 2) / 3
    repeat(rows) { rowIndex ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            val start = rowIndex * 3
            val end = minOf(start + 3, products.size)
            for (i in start until end) {
                val productName = getProductName(products[i].id)
                if(productName == null){
                    continue
                }
                ProductItem(product = products[i], navigate =  navigate, modifier = modifier)
            }
        }
    }
}

@Composable
private fun ProductItem(product: Product, modifier: Modifier, navigate: (GridNavProvider) -> Unit) {
    val productName = remember { getProductName(product.id) }
    val productIcon = remember { getProductIcon(product.id) }
    Card(
        modifier = modifier
            .height(100.dp)
            .widthIn(min = 100.dp, max = 110.dp)
            .clickable {
                if (product.id == "transactionFlow") navigate(GridNavProvider.CalcStep)
                else navigate(GridNavProvider.Banner)
            },
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(productIcon!!),
                contentDescription = productName,
                modifier = modifier.size(24.dp)
            )
            Text(
                text = productName!!,
                modifier = modifier.padding(top = 4.dp),
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
        }
    }
}

private fun getProductName(productId: String): String? {
    return productsMock.find { it.id == productId }?.name
}

private fun getProductIcon(productId: String): DrawableResource? {
    return productsMock.find { it.id == productId }?.icon
}
