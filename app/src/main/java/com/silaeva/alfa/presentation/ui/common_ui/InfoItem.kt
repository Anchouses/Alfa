package com.silaeva.alfa.presentation.ui.common_ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    bin: String = "",
    scheme: String? = null ?: "",
    brand: String? = null ?: "",
    cardNumberLength: Int? = null ?: 0,
    cardNumberLuhn: Boolean? = null ?: false,
    type: String? = null ?: "",
    prepaid: Boolean? = null ?: false,
    countryNumeric: String? = null ?: "",
    countryAlpha2: String? = null ?: "",
    countryName: String? = null ?: "",
    countryEmoji: String? = null ?: "",
    countryCurrency: String? = null ?: "",
    countryLatitude: Double? = null ?: .0,
    countryLongitude: Double? = null ?: .0,
    bankName: String? = null ?: "",
    bankUrl: String? = null ?: "",
    bankPhone: String? = null ?: "",
    bankCity: String? = null ?: "",
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "BIN",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(
                text = bin,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "SCHEME/NETWORK",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(
                text = scheme ?: "",
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "BRAND",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(
                text = brand ?: "",
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "CARD NUMBER",
                color = Color.Gray,
                fontSize = 16.sp
            )
            val luhn = when (cardNumberLuhn) {
                null -> ""
                true -> "YES"
                false ->  "NO"
            }
            val length = cardNumberLength?.toString() ?: ""
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Length: ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = length,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Text(
                    text = "   Luhn: ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = luhn,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "TYPE",
                color = Color.Gray,
                fontSize = 16.sp
            )
            Text(
                text = type ?: "",
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "PREPAID",
                color = Color.Gray,
                fontSize = 16.sp
            )

            val prepaidText = when (prepaid) {
                null -> ""
                true -> "YES"
                false ->  "NO"
            }

            Text(
                text = prepaidText,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "COUNTRY",
                color = Color.Gray,
                fontSize = 16.sp
            )
            if (countryName != null) {
                Text(
                    text = "${countryAlpha2 ?: ""} ${countryName ?: ""}",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Latitude: ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = countryLatitude?.toString() ?: "",
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Text(
                    text = "   Longitude: ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = countryLongitude?.toString() ?: "",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "BANK",
                color = Color.Gray,
                fontSize = 16.sp
            )
            if (bankName != "") {
                Text(
                    text = bankName ?: "",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }

            if (bankUrl != "") {
                Text(
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(bankUrl))
                        context.startActivity(intent)
                    },
                    text = bankUrl ?: "",
                    color = Color.Blue,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }

            if (bankPhone != "") {
                Text(
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse(bankPhone)
                        }
                        context.startActivity(intent)
                    },
                    text = bankPhone ?: "",
                    color = Color.Blue,
                    fontSize = 20.sp
                )
            }
            if (bankCity != "") {
                Text(
                    text = bankCity ?: "",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
    }
}