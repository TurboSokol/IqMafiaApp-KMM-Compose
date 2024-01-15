package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.turbosokol.iqmafiaapp.data.profile.ProfileUIModel
import com.turbosokol.iqmafiaapp.theme.Dimensions

@Composable
fun IQDropDownTextEdit(
    modifier : Modifier,
    allProfilesFromBE: List<ProfileUIModel>,
    profile: ProfileUIModel,
    onProfileChanged: (ProfileUIModel) -> Unit,
    playerNameColor: Color,

) {
    val mutableProfile = remember { mutableStateOf(profile) }
    val expandedState = remember { mutableStateOf(false) }
    val matchingList = remember { mutableStateOf(emptyList<ProfileUIModel>()) }
//         Column() {
    Card {
        BasicTextField(
            modifier = modifier,
            textStyle = TextStyle(color = playerNameColor, textAlign = TextAlign.Start),
            value = mutableProfile.value.nickName,
            onValueChange = { text ->
                mutableProfile.value = ProfileUIModel(nickName = text, id = -1)
                onProfileChanged(mutableProfile.value)
                if (text.isNotEmpty()) {
                    matchingList.value = allProfilesFromBE.filter {
                        it.nickName.contains(mutableProfile.value.nickName, ignoreCase = true)}
                } else { matchingList.value = emptyList() }
                expandedState.value = matchingList.value.isNotEmpty()
            },

        )

//        Box(
//            modifier = modifier
//                .height(Dimensions.Components.IQScoreRow.rowHeight)
//                .weight(0.23f)
//                .padding(top = 0.dp, bottom = 0.dp, end = Dimensions.Padding.small)
////                .align(Alignment.CenterVertically),
////            contentAlignment = Alignment.Center
//        )
//        {
            IQDropdownMenu(
                isExpanded = expandedState.value,
                content = {
                    matchingList.value.forEach { prof ->
                        Text(modifier = Modifier.clickable {
                            mutableProfile.value = prof
                            expandedState.value = false
                            onProfileChanged(mutableProfile.value)
                        }, text = prof.nickName)
                    }

                },
                dismiss = { expandedState.value = false },
                offset = DpOffset(0.dp, 0.dp),
                properties = PopupProperties()
            )
//        }


    }

}