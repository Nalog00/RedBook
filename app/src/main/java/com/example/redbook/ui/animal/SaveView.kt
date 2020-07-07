package com.example.redbook.ui.animal

import com.example.redbook.data.model.Animal

interface SaveView {
    fun setSave(models: List<Animal>)
}