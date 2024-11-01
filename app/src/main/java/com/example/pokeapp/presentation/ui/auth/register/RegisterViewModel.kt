package com.example.pokeapp.presentation.ui.auth.register

import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.di.ResourcesProvider
import com.example.pokeapp.domain.resource.Resource
import com.example.pokeapp.platform.NetworkHandler
import com.example.pokeapp.presentation.ui.auth.BaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val resourcesProvider: ResourcesProvider,

    ) : BaseViewModel() {
    val registerLiveData = MutableLiveData<Resource<Void?>>()
    val sendEmailLiveData = MutableLiveData<Resource<Void?>>()
    var name = MutableLiveData("")
    var lastName = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")

    fun registerUser() {
        if (!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
            auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)
                        user?.uid?.let {
                            val userReference = dbReference.child(it)
                            userReference.child("Name").setValue(name.value)
                            userReference.child("LastName").setValue(lastName.value)
                        }
                        registerLiveData.postValue(Resource.Success(null))

                    }else{
                        registerLiveData.postValue(
                            Resource.Failure(
                            false,
                            500,
                            "El usuario ya existe"
                        ))
                    }
                }
        }
    }

    fun verifyEmail(user: FirebaseUser?) {
        user?.sendEmailVerification()?.addOnCompleteListener { task: Task<Void> ->
            if (task.isComplete) {
                sendEmailLiveData.postValue(Resource.Success(null))
            } else {
                sendEmailLiveData.postValue(
                    Resource.Failure(
                        false,
                        500,
                        "Error al enviar el email"
                    )
                )
            }
        }
    }
}