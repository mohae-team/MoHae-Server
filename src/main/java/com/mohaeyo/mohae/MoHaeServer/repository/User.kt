package com.mohaeyo.mohae.MoHaeServer.repository

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "user")
@Entity
data class User(
        @Id
        var id: String,

        @Column(name = "password")
        var password: String,

        @Column(name = "username")
        var username: String,

        @Column(name = "imageString")
        var imageString: String,

        @Column(name = "address")
        var address: String,

        @Column(name = "description")
        var description: String
) {
        constructor(): this("", "", "", "", "",  "")
}