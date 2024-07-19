package org.stone.co.domain

import org.jetbrains.compose.resources.DrawableResource

public data class Product(
    val id: String,
    val name: String?,
    val icon: DrawableResource?,
    val order: Int
)