package com.satosa.tools.pruebaliverpool.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.satosa.tools.pruebaliverpool.R
import com.satosa.tools.pruebaliverpool.provider.ConversatiosProvider


class ChatsFragment : Fragment() {

    private lateinit var btnProfile: ImageView
    private lateinit var mConversationProvider : ConversatiosProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mConversationProvider = ConversatiosProvider()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    private fun initUI(view: View){
        btnProfile = view.findViewById(R.id.btn_profile)

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val result = mConversationProvider.getAllConversations(userId.toString())
        if(result.isNotEmpty()){
            print(result.toString())
        }
        btnProfile.setOnClickListener {
            findNavController().navigate(R.id.userProfileFragment)
        }
    }

}