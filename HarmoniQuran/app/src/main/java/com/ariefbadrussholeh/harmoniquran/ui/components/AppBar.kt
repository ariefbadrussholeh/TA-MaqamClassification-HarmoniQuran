package com.ariefbadrussholeh.harmoniquran.ui.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    onBackClicked: (() -> Unit)
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
//        actions = {
//            IconButton(onClick = { expanded = !expanded }) {
//                Icon(
//                    imageVector = Icons.Default.MoreVert,
//                    contentDescription = null
//                )
//            }
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//            ) {
//                DropdownMenuItem(
//                    text = { Text(text = "Tentang Maqam") },
//                    onClick = { /*TODO*/ }
//                )
//                DropdownMenuItem(
//                    text = { Text(text = "Tentang Aplikasi") },
//                    onClick = { /*TODO*/ }
//                )
//            }
//        }
    )
}

@Preview()
@Composable
private fun LightThemePreview() {
    HarmoniQuranTheme {
        CustomTopAppBar(title = "Daftar Audio", onBackClicked = {})
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkThemePreview() {
    HarmoniQuranTheme {
        CustomTopAppBar(title = "Daftar Audio", onBackClicked = {})
    }
}