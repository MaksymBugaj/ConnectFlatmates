package com.connect.connectflatmates.ui.createaccount

import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.state.login.LoginState
import com.connect.connectflatmates.state.login.LoginStateManager

class CreateAccountViewModel(
    private val userRepository: UserRepository,
    private val loginStateManager: LoginStateManager
) : ViewModel() {

    val name = ObservableField<String>()
    val surname = ObservableField<String>()
    val email = ObservableField<String>()
    val login = ObservableField<String>()
    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()

    private val _observeCreate = MutableLiveData<Boolean>()
    val observeCreateAccount: LiveData<Boolean> = _observeCreate

    private val _observeInput = MutableLiveData<CreatingAccount>()
    val observeInput: LiveData<CreatingAccount> = _observeInput


    fun insert(userProfile: UserProfile) {
        userRepository.insertUser(userProfile)
    }


    private fun setState(state: LoginState) {
        loginStateManager.setState(state)
    }

    fun onCreateClick() {
        Log.d("NOPE","click click:")
        var czujka6 = false

        if (name.get().isNullOrEmpty()) {
            _observeInput.value = CreatingAccount.NoName
            Log.d("NOPE","Problem:")
            czujka6 = true
        }

        if (surname.get().isNullOrEmpty()) {
            _observeInput.value = CreatingAccount.NoSurname
            Log.d("NOPE","Problem:")
            czujka6 = true
        }

        if(email.get().isNullOrEmpty()){
            _observeInput.value = CreatingAccount.NoEmail
            czujka6 = true
        }

        if(!email.get().isNullOrEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()) {
                _observeInput.value = CreatingAccount.WrongEmail
                czujka6 = true
            }
        }

        if(login.get().isNullOrEmpty()){
            _observeInput.value = CreatingAccount.NoLogin
            czujka6 = true
        }

        //todo add login and email verification

        if(password.get().isNullOrEmpty()){
            _observeInput.value = CreatingAccount.NoPassword
            czujka6 = true
        }

        if(repeatPassword.get().isNullOrEmpty()){
            _observeInput.value = CreatingAccount.NoRepeatedPassword
            czujka6 = true
        }

        if(password.get() != repeatPassword.get()){
            _observeInput.value = CreatingAccount.PasswordDoesntMatch
            czujka6 = true
        }

        if(czujka6) return
        val userProfile = UserProfile(
            name = name.get()!!,
            surname = surname.get()!!,
            email = email.get()!!,
            login = login.get()!!,
            password = password.get()!!
        )
        insert(userProfile)
        setState(LoginState.AccountCreated)
        _observeCreate.value = true
//        insert(userProfile)

        /*if (!name.get().isNullOrEmpty()
            && !surname.get().isNullOrEmpty()
            && !email.get().isNullOrEmpty()
            && !login.get().isNullOrEmpty()
            && !password.get().isNullOrEmpty()
            && password.get() == repeatPassword.get()
        ) {
            val userProfile = UserProfile(
                name = name.get()!!,
                surname = surname.get()!!,
                email = email.get()!!,
                login = login.get()!!,
                password = password.get()!!
            )
            insert(userProfile)
            setState(LoginState.AccountCreated)
            _observeCreate.value = true
        } else {
            Log.d("NOPE","Problem:")
        }*/
    }


}

sealed class CreatingAccount() {
    object NoName : CreatingAccount()
    object NoSurname : CreatingAccount()
    object NoLogin : CreatingAccount()
    object DuplicatedLogin : CreatingAccount()
    object WrongEmail : CreatingAccount()
    object DuplicatedEmail : CreatingAccount()
    object NoEmail : CreatingAccount()
    object NoPassword : CreatingAccount()
    object NoRepeatedPassword : CreatingAccount()
    object PasswordDoesntMatch : CreatingAccount()
}
