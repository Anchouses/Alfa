package com.silaeva.alfa.presentation.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.silaeva.alfa.presentation.ui.common_ui.InfoItem
import org.koin.androidx.compose.koinViewModel


@Composable
fun InfoScreen(
    paddingValues: PaddingValues,
    onErrorShow: (Boolean) -> Unit,
    viewModel: InfoViewModel = koinViewModel()
) {

    val binNum = remember { mutableStateOf(viewModel.binNum) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(scrollState),
    ) {
        EnterBin(
            onEnterBin = { bin ->
                viewModel.getInfo(bin)
                binNum.value = bin
            },
            onErrorShow = {isError ->
                onErrorShow(isError)
            }
        )
        val infoState = viewModel.info.collectAsState()
        val info = infoState.value

        InfoItem(
            bin = binNum.value,
            scheme = info?.scheme,
            brand = info?.brand,
            cardNumberLength = info?.number?.length,
            cardNumberLuhn = info?.number?.luhn,
            type = info?.type,
            prepaid = info?.prepaid,
            countryName = info?.country?.name,
            countryNumeric = info?.country?.numeric,
            countryAlpha2 = info?.country?.alpha2,
            countryEmoji = info?.country?.emoji,
            countryCurrency = info?.country?.currency,
            countryLatitude = info?.country?.latitude,
            countryLongitude = info?.country?.longitude,
            bankName = info?.bank?.name,
            bankUrl = info?.bank?.url,
            bankPhone = info?.bank?.phone,
            bankCity = info?.bank?.city,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun EnterBin(
    onEnterBin: (String) -> Unit,
    onErrorShow: (Boolean) -> Unit
) {

    val bin = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        OutlinedTextField(
            modifier = Modifier.weight(0.5f),
            value = bin.value,
            onValueChange = { newValue ->
                bin.value = newValue
                isError.value = false
            },
            placeholder = { Text(text = "BIN") },
            isError = isError.value,
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                errorPlaceholderColor = Color.Red,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray,
                errorBorderColor = Color.Red,
            )
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Button(
            modifier = Modifier
                .height(56.dp),
            onClick = {
                if (bin.value.matches(Regex("^[0-9]+$")) && (bin.value.length == 6 || bin.value.length == 8)) {
                    onEnterBin(bin.value)
                } else {
                    isError.value = true
                    onErrorShow(isError.value)
                }
            },
            content = {
                Text(
                    text = "Enter",
                    color = Color.White
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White
            )
        )
    }
}