package com.example.queueless.ui.common.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

    placeholder: String = "Search...",
    leadingIcon: ImageVector? = Icons.Outlined.Search,
    trailingIcon: ImageVector? = null,

    onLeadingIconClick: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null,

    imeAction: ImeAction = ImeAction.Search
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            ),

        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },

        leadingIcon = {
            leadingIcon?.let {
                IconButton(
                    onClick = { onLeadingIconClick?.invoke() }
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Leading Icon",
                        tint = Color.Gray
                    )
                }
            }
        },

        trailingIcon = {
            trailingIcon?.let {
                IconButton(
                    onClick = { onTrailingIconClick?.invoke() }
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Trailing Icon",
                        tint = Color.Gray
                    )
                }
            }
        },

        singleLine = true,
        enabled = enabled,
        readOnly = readOnly,

        shape = RoundedCornerShape(24.dp),

        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),



        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = Color(0xFF2F66F3)
        )
    )
}
