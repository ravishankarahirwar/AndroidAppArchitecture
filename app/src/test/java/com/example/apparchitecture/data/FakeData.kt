package com.example.apparchitecture.data

import com.example.apparchitecture.data.Users

fun createFakeUsers(): Users {
    val users = Users()

    // Add fake UsersItem instances
    users.add(Users.UsersItem(
        address = "4434 Rachel Meadows",
        company = "Keith",
        country = "Serbia",
        email = "Madalyn.Okuneva56@hotmail.com",
        id = 1,
        name = "Olen Quitzon",
        phone = "442.993.9351 x5456",
        photo = "https://json-server.dev/ai-profiles/0.png",
        state = "Kentucky",
        username = "Skyla_Welch70",
        zip = "30802-2741"
    ))

    users.add(Users.UsersItem(
        address = "1234 Elm Street",
        company = "Acme Corp",
        country = "USA",
        email = "john.doe@example.com",
        id = 2,
        name = "John Doe",
        phone = "555-1234",
        photo = "https://json-server.dev/ai-profiles/1.png",
        state = "California",
        username = "johndoe",
        zip = "90210"
    ))

    users.add(Users.UsersItem(
        address = "5678 Oak Avenue",
        company = "Globex",
        country = "USA",
        email = "jane.doe@example.com",
        id = 3,
        name = "Jane Doe",
        phone = "555-5678",
        photo = "https://json-server.dev/ai-profiles/2.png",
        state = "Texas",
        username = "janedoe",
        zip = "73301"
    ))

    return users
}