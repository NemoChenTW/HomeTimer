package com.nemochen.hometimer.model.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemochen.hometimer.model.repository.CountdownEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class CountdownEntity {
    companion object {
        const val TABLE_NAME = "CountdownTable"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name: String = ""
    var time: Long = 0L
}