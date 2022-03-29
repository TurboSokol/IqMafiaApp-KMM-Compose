package com.turbosokol.iqmafiaapp.common.account.redux

import com.turbosokol.iqmafiaapp.core.redux.Action
import com.turbosokol.iqmafiaapp.core.redux.Effect
import com.turbosokol.iqmafiaapp.core.redux.GeneralState

data class AccountState(
    val loadProgress: Boolean,
    val saveProgress: Boolean,

    val nickname: String,
    val name: String,
    val secondName: String,
    val email: String,
    val password: String
): GeneralState {
    companion object {
        fun getDefaultState(): AccountState {
            return AccountState(
                loadProgress = false,
                saveProgress = false,
                nickname = "",
                name = "",
                secondName = "",
                email = "",
                password = ""
            )
        }
    }
}

sealed class AccountAction: Action {
    object CleanUp: AccountAction()
    object CheckAccountState: AccountAction()

    data class LoadAccountState(val progress: Boolean): AccountAction()
    data class LoadAccountStateDone(val accountState: AccountState): AccountAction()

    object SaveAccountState: AccountAction()
    object SaveAccountStateDone: AccountAction()

    object DeleteAccount: AccountAction()
    object DeleteAccountDone: AccountAction()

    data class EditAccount(val name: String, val secondName: String, val email: String): AccountAction()
    data class VerifyCurrentPassword(val password: String): AccountAction()
    data class ChangePassword(val currentPassword: String, val newPassword: String, val confirmNewPassword: String): AccountAction()

    data class AccountError(val errorMessage: String): AccountAction()
}

data class EditAccountEffect(val success: Boolean): Effect
data class ChangePasswordEffect(val success: Boolean): Effect