package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_save.*

class SaveFragment: Fragment(R.layout.fragment_save), AnimalItemClickListener,AnimalView {
    private val mYAdapter = AnimalListAdapter(this)
    private lateinit var dao: AnimalDao
    private lateinit var presenter: SavePresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSave.adapter = mYAdapter
        recyclerViewSave.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        dao = RedBookDatabase.getInstance(requireContext()).dao()
        presenter = SavePresenter(dao, this)
        presenter.saveAnimal()
    }

    override fun onStart() {
        super.onStart()
        presenter.saveAnimal()
    }


    override fun onAnimalItemClick(id: Int) {
        val nIntent = Intent(requireActivity(), DetailActivity::class.java)
        nIntent.putExtra(DetailActivity.ANIMAL_ID, id)
        startActivity(nIntent)
    }

    override fun setData(models: List<Animal>) {
        mYAdapter.models = dao.saveAnimal()
    }
}