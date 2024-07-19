package org.stone.co.navigation

sealed class GridNavProvider {
    object CalcStep : GridNavProvider()
    object Banner : GridNavProvider()
}