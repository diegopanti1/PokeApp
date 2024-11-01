package com.example.pokeapp.presentation.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.di.ResourcesProvider
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.platform.NetworkHandler
import com.example.pokeapp.presentation.ui.auth.BaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val resourcesProvider: ResourcesProvider
): BaseViewModel() {
    val loginLiveData = MutableLiveData<Resource<Void?>>()
    val resetPasswordLiveData = MutableLiveData<Resource<Void?>>()
    var email = MutableLiveData("")
    var password = MutableLiveData("")

    fun loginUser() {
        if (!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            auth.signInWithEmailAndPassword(email.value!!, password.value!!)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        loginLiveData.postValue(Resource.Success(null))
                    }else{
                        loginLiveData.postValue(Resource.Failure(false, 500, "Error en la autenticación"))
                    }
                }
        }
    }

    fun resetPassword() {
        if (!email.value.isNullOrEmpty()) {
            auth.sendPasswordResetEmail(email.value!!)
                .addOnCompleteListener { task: Task<Void> ->
                    if (task.isComplete) {
                        resetPasswordLiveData.postValue(Resource.Success(null))
                    }else{
                        resetPasswordLiveData.postValue(Resource.Failure(false, 500, "Error en la autenticación"))
                    }
                }
        }
    }
}