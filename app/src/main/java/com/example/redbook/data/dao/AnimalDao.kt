package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.redbook.data.model.Animal


@Dao
interface AnimalDao {
    @Query( "SELECT * FROM book where type = :type")
    fun getAllAnimals(type: Int): List<Animal>

    @Query("SELECT * FROM book where id = :id")
    fun getAnimalById(id: Int): Animal
}