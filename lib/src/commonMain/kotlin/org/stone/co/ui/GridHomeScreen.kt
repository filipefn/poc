package org.stone.co.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.stone.co.component.ProductsListComponent
import org.stone.co.domain.Product
import org.stone.co.navigation.ExternalNavigation
import org.stone.co.navigation.GridNavProvider
import poc.lib.generated.resources.Res
import poc.lib.generated.resources.calculadora_taxas
import poc.lib.generated.resources.link_pagamento
import poc.lib.generated.resources.minhas_vendas
import poc.lib.generated.resources.pix
import poc.lib.generated.resources.recarga_celular
import poc.lib.generated.resources.transferir_dinheiro
import poc.lib.generated.resources.vender_agora

public class GridHomeScreen(val externalNavigation: ExternalNavigation) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ProductsListComponent {
            when (it) {
                GridNavProvider.Banner -> externalNavigation.toBanner
                GridNavProvider.CalcStep -> navigator push CalcHomeScreen
            }
        }
    }
}


val productsMock = listOf(
    Product(
        id = "transactionFlow",
        name = "Vender Agora",
        icon = Res.drawable.vender_agora,
        order = 1
    ),
    Product(
        id = "salesHistory",
        name = "Minhas Vendas",
        icon = Res.drawable.minhas_vendas,
        order = 2
    ),
    Product(
        id = "mobileRecharge",
        name = "Recarga de celular",
        icon = Res.drawable.recarga_celular,
        order = 3
    ),
    Product(
        id = "bankingPix",
        name = "Pix",
        icon = Res.drawable.pix,
        order = 4
    ),
    Product(
        id = "transfer",
        name = "Transferir dinheiro",
        icon = Res.drawable.transferir_dinheiro,
        order = 5
    ),
    Product(
        id = "calculator",
        name = "Calculadora de Taxas",
        icon = Res.drawable.calculadora_taxas,
        order = 6
    ),
    Product(
        id = "paymentLinks",
        name = "Links de Pagamento",
        icon = Res.drawable.link_pagamento,
        order = 7
    ),
)