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

class SaveFragment: Fragment(R.layout.fragment_save), SaveView {
    private val mYAdapter = AnimalListAdapter()
    private lateinit var dao: AnimalDao
    private lateinit var presenter: SavePresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSave.adapter = mYAdapter
        recyclerViewSave.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        mYAdapter.setOnItemClickLisener {id->
            val mIntent = Intent(requireActivity(), DetailActivity::class.java)
            mIntent.putExtra(DetailActivity.ANIMAL_ID, id)
            startActivity(mIntent)
        }
        dao = RedBookDatabase.getInstance(requireContext()).dao()
        presenter = SavePresenter(dao,this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getSave()
    }

    override fun setSave(models: List<Animal>) {
        mYAdapter.models = models
    }

}