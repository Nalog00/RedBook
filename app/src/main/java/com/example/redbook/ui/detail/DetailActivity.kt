package com.example.redbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_animal.view.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val ANIMAL_ID = "animalID"
    }

    private  var animalId = 0
    private lateinit var currentAnimal: Animal
    private lateinit var  dao: AnimalDao
    private var menuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Details")

        dao = RedBookDatabase.getInstance(this).dao()
        animalId = intent.getIntExtra(ANIMAL_ID,0)
        currentAnimal = dao.getAnimalById(animalId)


        tvStatusContent.text = currentAnimal.status
        tvHabitatContent.text = currentAnimal.habitat
        tvStatusPropagation.text = currentAnimal.propagation
        tvQuantityContent.text = currentAnimal.quantity
        tvLifestyleContent.text = currentAnimal.lifestyle
        tvLimitingFactorsContent.text =currentAnimal.limitingFactors
        tvBreedingContent.text =currentAnimal.breeding
        tvSecurityContent.text = currentAnimal.security


        Glide.with(this)
            .load(resources.getIdentifier("picture$animalId", "drawable",packageName))
            .into(ivDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem = menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
            R.id.item_bookmark->setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setFavorite(){
        if(currentAnimal.isFavorite==null) currentAnimal.isFavorite = 1
        else currentAnimal.isFavorite = 1-currentAnimal.isFavorite!!
        setFavoriteIcon()
        dao.updateAnimal(currentAnimal)
        onRestart()
    }

    private  fun setFavoriteIcon(){
        if (currentAnimal.isFavorite == 1){
        menuItem?.setIcon(R.drawable.ic_baseline_bookmark1)
        }else{
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark2)
        }
    }
    
}