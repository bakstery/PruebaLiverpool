package com.satosa.tools.pruebaliverpool.provider

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthenticationProvider {
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String?, password: String?): Task<AuthResult?> {
        return mAuth.signInWithEmailAndPassword(email!!, password!!)
    }

    fun logout() {
        mAuth.signOut()
    }
}