package com.test.jetpackcompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInputText(value: String, label: String, OnValueChange: (Any?) -> Unit) {
    var inputFieldValue by remember { mutableStateOf("") }
    OutlinedTextField(value = value, label = {
        Text(text = label, color = Color.White)
    }, onValueChange = { inputValue ->
        inputFieldValue = inputValue
        OnValueChange(inputFieldValue)

    }, colors = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = Color.LightGray
    ), modifier = Modifier.fillMaxWidth()
    )
}