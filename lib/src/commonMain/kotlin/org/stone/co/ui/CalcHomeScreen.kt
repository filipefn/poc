package org.stone.co.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

internal object CalcHomeScreen : Screen {

    @Composable
    override fun Content() {
        CalcScreen()

    }

    @Composable
    internal fun CalcScreen(
        modifier: Modifier = Modifier
    ) {
            var amount by remember { mutableStateOf("50,00") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Vender Agora",
                    modifier = modifier.padding(top = 4.dp),
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = "Valor",
                    modifier = modifier.padding(top = 4.dp),
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = "R\$ $amount",
                    modifier = modifier.padding(top = 4.dp),
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )

                Keypad(onNumberClick = { number ->
                    amount = if (amount == "0,00") {
                        number
                    } else {
                        "$amount$number"
                    }
                })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { /* Handle "Calcular taxa" */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Calcular taxa")
                    }
                    Button(
                        onClick = { /* Handle "Cobrar: R$ 50,00" */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Cobrar: R$ $amount")
                    }
                }
            }
        }

        @Composable
        fun Keypad(onNumberClick: (String) -> Unit) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (row in 0..2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        for (col in 1..3) {
                            val number = row * 3 + col
                            NumberButton(number = number.toString(), onClick = onNumberClick)
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    NumberButton(number = "+", onClick = onNumberClick)
                    NumberButton(number = "0", onClick = onNumberClick)
                    NumberButton(number = "←", onClick = {
                        // Implement the backspace functionality
                    })
                }
            }
        }

        @Composable
        fun NumberButton(number: String, onClick: (String) -> Unit) {
            Button(
                onClick = { onClick(number) },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Text(text = number)
            }
        }
}