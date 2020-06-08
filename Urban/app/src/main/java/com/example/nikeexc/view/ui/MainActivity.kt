package com.example.nikeexc.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeexc.R
import com.example.nikeexc.network.repository.UrbanRepository
import com.example.nikeexc.viewmodel.UrbanViewModel
import com.example.nikeexc.viewmodel.VMFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listview.*


class MainActivity : AppCompatActivity() {
    lateinit var mSearchText: SearchView
    lateinit var viewModel: UrbanViewModel
    lateinit var rView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = rv
        mSearchText = search
        viewModel =
            ViewModelProvider(this, VMFactory(UrbanRepository())).get(UrbanViewModel::class.java)


        setUp()

    }

    private fun setupRV() {
        val adapter = viewModel.mAdapter
        rView.adapter = adapter
        rView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun setUp() {
        viewModel.searchDefinitions(search)

        like.setOnClickListener {
            viewModel.sortDefinitions(true)
            rv.smoothScrollToPosition(0)
        }
        dislike.setOnClickListener {
            viewModel.sortDefinitions(false)
            rv.smoothScrollToPosition(0)
        }

        val errorObserver = Observer<Boolean> {
            Toast.makeText(this, "Nothing here to see", Toast.LENGTH_LONG).show()
        }



        viewModel.errorLiveData.observe(this, errorObserver)
        setupRV()
    }
}
