package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao

class SavePresenter(private val dao: AnimalDao,private val view: AnimalView) {
    fun saveAnimal(){
        view.setData(dao.saveAnimal())
    }
}