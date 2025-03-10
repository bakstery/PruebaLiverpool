package com.satosa.tools.pruebaliverpool.provider

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.satosa.tools.pruebaliverpool.models.ConverstionsModel

class ConversatiosProvider {
    val db = Firebase.firestore


    fun getAllConversations(userId: String): MutableList<ConverstionsModel> {
        val dataSets = mutableListOf<ConverstionsModel>()
        db.collection("conversatios").get().addOnSuccessListener { documents ->
            for (document in documents) {
                if(document.id == userId) {
                    val nombre = document["nombre"].toString()
                    val participantes = document["participantes"].toString()
                    val chats = ConverstionsModel(nombre = nombre,participantes = participantes)
                    dataSets.add(chats)
                }
            }
        }
        return dataSets
    }
}