package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.redbook.data.model.Animal


@Dao
interface AnimalDao {
    @Query( "SELECT * FROM book where type = :type")
    fun getAllAnimals(type: Int): List<Animal>

    @Query("SELECT * FROM book where id = :id")
    fun getAnimalById(id: Int): Animal

    @Query("SELECT * From book where  type=:type and nameRus like:word or type=:type and  nameEng like:word")
    fun searchAnimalByName(type: Int, word: String) : List<Animal>

    @Query("Select * from book where isFavorite = 1")
    fun saveAnimal(): List<Animal>

    @Update
    fun updateAnimal(animal: Animal)
}