package com.ariefbadrussholeh.harmoniquran.ui.components

import android.app.admin.DevicePolicyManager
import android.content.res.Configuration
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model.AudioItemUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme
import jodd.util.MapEntry

@Composable
fun CustomElevatedIconButton(
    icon: ImageVector,
    size: Dp,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor
        ),
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(size)
            )
        }
    }
}

@Preview()
@Composable
private fun LightThemePreview() {
    HarmoniQuranTheme {
        CustomElevatedIconButton(
            icon = Icons.Default.PlayArrow,
            size = 64.dp,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = {}
        )
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkThemePreview() {
    HarmoniQuranTheme {
        CustomElevatedIconButton(
            icon = Icons.Default.PlayArrow,
            size = 64.dp,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = {}
        )
    }
}

