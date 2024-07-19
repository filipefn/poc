package org.stone.sample
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.stone.co.component.ProductsListComponent


@Composable
fun App() {
    MaterialTheme {
        Scaffold(
            content = {
                ProductsListComponent(Modifier.padding(it)) {

                }
            }
        )
    }
}

