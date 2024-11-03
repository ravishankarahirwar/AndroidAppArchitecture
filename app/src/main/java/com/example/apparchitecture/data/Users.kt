package com.example.apparchitecture.data


import com.google.gson.annotations.SerializedName

class Users : ArrayList<Users.UsersItem>(){
    data class UsersItem(
        @SerializedName("address")
        val address: String, // 4434 Rachel Meadows
        @SerializedName("company")
        val company: String, // Keith
        @SerializedName("country")
        val country: String, // Serbia
        @SerializedName("email")
        val email: String, // Madalyn.Okuneva56@hotmail.com
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("name")
        val name: String, // Olen Quitzon
        @SerializedName("phone")
        val phone: String, // 442.993.9351 x5456
        @SerializedName("photo")
        val photo: String, // https://json-server.dev/ai-profiles/0.png
        @SerializedName("state")
        val state: String, // Kentucky
        @SerializedName("username")
        val username: String, // Skyla_Welch70
        @SerializedName("zip")
        val zip: String // 30802-2741
    )
}