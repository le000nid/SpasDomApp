package com.example.spasdomworkerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spasdomworkerapp.models.Order

@Entity
data class DatabaseOrderItem constructor(
    val date: String,
    val address: String,
    val time: String,
    val problem: String,
    val active: Boolean,
    val finished: Boolean,
    @PrimaryKey
    val id: Int
    )

fun List<DatabaseOrderItem>.asDomainOrder(): List<Order> {
    return map {
        Order(
            date = it.date,
            address = it.address,
            time = it.time,
            problem = it.problem,
            active = it.active,
            finished  = it.finished,
            id = it.id
        )
    }
}