package com.ariefbadrussholeh.harmoniquran.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ariefbadrussholeh.harmoniquran.R
import com.ariefbadrussholeh.harmoniquran.ui.screen.audiolist.model.AudioItemUIState
import com.ariefbadrussholeh.harmoniquran.ui.theme.HarmoniQuranTheme

@Composable
fun AudioItem(
    modifier: Modifier = Modifier,
    itemState: AudioItemUIState
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                itemState.onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(4.dp),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = itemState.displayName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = itemState.size,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview()
@Composable
private fun LightThemePreview() {
    HarmoniQuranTheme {
        AudioItem(
            itemState = AudioItemUIState(
                id = "id",
                displayName = "display_name",
                size = "size",
                onClick = {}
            )
        )
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkThemePreview() {
    HarmoniQuranTheme {
        AudioItem(
            itemState = AudioItemUIState(
                id = "id",
                displayName = "display_name",
                size = "size",
                onClick = {}
            )
        )    }
}

