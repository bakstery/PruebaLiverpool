package com.satosa.tools.pruebaliverpool.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.satosa.tools.pruebaliverpool.R
import com.satosa.tools.pruebaliverpool.provider.AuthenticationProvider


class LoginFragment : Fragment() {
    private lateinit var btnLogin: Button
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    // authentication
    private lateinit var mAuthProvider : AuthenticationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuthProvider = AuthenticationProvider()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mAuthProvider.logout()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }


    private fun initUI(view: View) {
        btnLogin = view.findViewById(R.id.btn_login)
        etEmail = view.findViewById(R.id.et_email)
        etPassword = view.findViewById(R.id.et_password)

        btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (password.length >= 6) {
                mAuthProvider.login(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(), "Los datos son correctos, ¡Bienvenido!",
                            Toast.LENGTH_LONG
                        ).show()

                        goToChatsFragment()

                    } else {
                        Toast.makeText(
                            requireContext(), "La contraseña o el password es incorrecto",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(),"La contraseña debe tener más de 6 caracteres",
                    Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireContext(),"Debe ingresar un usuario y una contraseña", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToChatsFragment() {
        findNavController().navigate(R.id.chatsFragment)
    }
}