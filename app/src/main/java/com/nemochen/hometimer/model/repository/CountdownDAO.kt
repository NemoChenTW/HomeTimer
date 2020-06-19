package com.nemochen.hometimer.model.repository

import androidx.room.*

@Dao
interface CountdownDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CountdownEntity)

    @Query("SELECT * FROM CountdownTable")
    fun gets(): List<CountdownEntity>

    @Update
    fun update(item: CountdownEntity)

    @Delete
    fun delete(item: CountdownEntity)
}