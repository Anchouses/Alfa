package com.silaeva.alfa.presentation.ui.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.silaeva.alfa.R
import com.silaeva.alfa.domain.data_model.BinCheckedModel

@Composable
fun HistoryItem(
    binCheckedModel: BinCheckedModel
) {
    val isPushed = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isPushed.value = !isPushed.value
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = binCheckedModel.bin.toString()
            )

            if (isPushed.value) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                    contentDescription = null
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        }

        if (isPushed.value) {
            InfoItem(
                bin = binCheckedModel.bin.toString(),
                scheme = binCheckedModel.binInfoChecked.scheme,
                brand = binCheckedModel.binInfoChecked.brand,
                cardNumberLength = binCheckedModel.binInfoChecked.number.length,
                cardNumberLuhn = binCheckedModel.binInfoChecked.number.luhn,
                type = binCheckedModel.binInfoChecked.type,
                prepaid = binCheckedModel.binInfoChecked.prepaid,
                countryName = binCheckedModel.binInfoChecked.country.name,
                countryNumeric = binCheckedModel.binInfoChecked.country.numeric,
                countryAlpha2 = binCheckedModel.binInfoChecked.country.alpha2,
                countryEmoji = binCheckedModel.binInfoChecked.country.emoji,
                countryCurrency = binCheckedModel.binInfoChecked.country.currency,
                countryLatitude = binCheckedModel.binInfoChecked.country.latitude,
                countryLongitude = binCheckedModel.binInfoChecked.country.longitude,
                bankName = binCheckedModel.binInfoChecked.bank.name,
                bankUrl = binCheckedModel.binInfoChecked.bank.url,
                bankPhone = binCheckedModel.binInfoChecked.bank.phone,
                bankCity = binCheckedModel.binInfoChecked.bank.city
            )
        }
    }
}