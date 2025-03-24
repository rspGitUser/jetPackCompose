package com.test.jetpackcompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LoginInputText(value: String, label: String, OnValueChange: (Any?) -> Unit) {
    var inputFieldValue by remember { mutableStateOf("") }
    OutlinedTextField(value = value, label = {
        Text(text = label)
    }, onValueChange = { inputValue ->
        inputFieldValue = inputValue
        OnValueChange(inputFieldValue)

    }, modifier = Modifier.fillMaxWidth())
}