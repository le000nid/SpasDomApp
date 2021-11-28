package com.example.spasdomworkerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spasdomworkerapp.domain.Order

@Entity
data class DatabaseOrderItem constructor(
    val date: String,
    val address: String,
    val time: String,
    val problem: String,
    val finished: Boolean,
    val canceled: Boolean,
    @PrimaryKey
    val id: Int
    )

fun List<DatabaseOrderItem>.asDomainModel(): List<Order> {
    return map {
        Order(
            date = it.date,
            address = it.address,
            time = it.time,
            problem = it.problem,
            finished  = it.finished,
            canceled = it.canceled,
            id = it.id
        )
    }
}