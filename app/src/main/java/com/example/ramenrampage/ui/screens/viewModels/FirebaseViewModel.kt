package com.example.ramenrampage.ui.screens.viewModels

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Locale


class FirebaseViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private var _email = mutableStateOf("")
    val email = _email

    private var _password = mutableStateOf("")
    val password = _password

    private var _username = mutableStateOf("")
    val username = _username

    private var _userProfileName = mutableStateOf("")
    val userProfileName = _userProfileName // Used to display the username in the profile

    private var _memberSince = mutableStateOf("")
    val memberSince = _memberSince



    fun registerUser() {
        val emailValue = email.value
        val passwordValue = password.value
        val usernameValue = username.value
        if (emailValue.isNotBlank() && passwordValue.isNotBlank() && usernameValue.isNotBlank()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Create the user in Firebase Authentication
                    auth.createUserWithEmailAndPassword(emailValue, passwordValue).await()

                    // Store the username in Firestore under the 'users' collection
                    val currentUser = auth.currentUser
                    currentUser?.let { user ->
                        val userId = user.uid
                        val userMap = hashMapOf(
                            "username" to usernameValue,
                            "email" to emailValue
                        )
                        firestore.collection("users").document(userId).set(userMap).await()
                    }
                    fetchUserProfileName()
                } catch (e: Exception) {
                    // Handle registration failure
                    println(e.message)
                }
            }
        }
    }

    // Fetch the username from Firestore
     fun fetchUserProfileName() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val userId = user.uid
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userSnapshot = firestore.collection("users").document(userId).get().await()
                    _userProfileName.value = userSnapshot.getString("username") ?: ""
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }



    // Fetch the user's member since date
     fun fetchMemberSinceDate() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val creationTimestamp = user.metadata?.creationTimestamp ?: 0
            // Format the date
            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
            _memberSince.value = dateFormat.format(creationTimestamp)
        }
    }

    fun loginUser() {
        val emailValue = email.value
        val passwordValue = password.value
        if (emailValue.isNotBlank() && passwordValue.isNotBlank()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(emailValue, passwordValue).await()
                    fetchUserProfileName()

                } catch (e: Exception) {
                    // Handle login failure
                    println(e.message)
                }
            }
        }
    }



    fun logOut() {
        auth.signOut()
    }




}