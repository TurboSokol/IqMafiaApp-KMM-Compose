package com.turbosokol.iqmafiaapp.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.turbosokol.iqmafiaapp.data.profile.ProfileUIModel


@Composable
fun IQDropDownTextEdit(
    allProfilesFromBE: List<ProfileUIModel>,
    profile: ProfileUIModel,
    onProfileChanged: (ProfileUIModel) -> Unit
) {
    val mutableProfile = remember { mutableStateOf(profile) }
    val expandedState = remember { mutableStateOf(false) }
    val matchingList = remember { mutableStateOf(emptyList<ProfileUIModel>()) }
            
    Card {
        BasicTextField(
            value = mutableProfile.value.nickName,
            onValueChange = { text ->
                mutableProfile.value = ProfileUIModel(nickName = text)
                matchingList.value = allProfilesFromBE.filter {
                    it.nickName.contains(mutableProfile.value.nickName)}
                if(matchingList.value.isNotEmpty()) {expandedState.value = true}
            }
        )
        IQDropdownMenu(
            isExpanded = expandedState.value,
            content = {
                matchingList.value.forEach { prof ->
                    Text(text = prof.nickName)
                }

            },
            dismiss = { expandedState.value = false },
            offset = DpOffset(0.dp, 0.dp),
            properties = PopupProperties()
        )
    }

}