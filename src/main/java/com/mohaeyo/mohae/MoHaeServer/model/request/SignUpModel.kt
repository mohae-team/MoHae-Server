package com.mohaeyo.mohae.MoHaeServer.model.request

data class SignUpModel(
        val username: String,
        val id: String,
        val password: String,
        val address: String
)