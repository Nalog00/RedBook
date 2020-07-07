package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class AnimalPresenter(private val dao: AnimalDao) {


    private  var setData: (models: List<Animal>)-> Unit = {
        println("relizatica qilinbadi")
    }


    fun setFunctionBody(qalegen:(a: List<Animal>)-> Unit){
        this.setData = qalegen
    }




    fun getAllAnimals(type: Int){
        setData.invoke(dao.getAllAnimals(type))
    }
}