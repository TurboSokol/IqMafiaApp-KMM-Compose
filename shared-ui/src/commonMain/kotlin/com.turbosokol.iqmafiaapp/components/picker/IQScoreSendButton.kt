package com.turbosokol.iqmafiaapp.components.picker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turbosokol.iqmafiaapp.theme.Dimensions

@Composable
fun IQScoreSendButton () {
    Button(onClick = {}, modifier = Modifier.background(MaterialTheme.colorScheme.onBackground),
        elevation = ButtonDefaults.buttonElevation(Dimensions.Elevation.xlarge)
    , border = BorderStroke(3.dp, color = MaterialTheme.colorScheme.tertiary)
    )
    {
        Text("Send data to the cloud")
    }

//    FloatingActionButton(
//        onClick = {},
////        modifier = modifier,
//        content = { Text("Send Data") },
//        shape = CircleShape,
//        containerColor = MaterialTheme.colorScheme.tertiary
//    )
}