package com.surelabs.indonesia.recyclerviewpagination

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surelabs.indonesia.recyclerviewpagination.adapter.AdapterSimple
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var page = 1
    private val inputList = mutableListOf<String>()
    private lateinit var adapterSimple: AdapterSimple
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        inputRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    ++page
                    //call your api in here
                    Toast.makeText(this@MainActivity, "$page", Toast.LENGTH_SHORT).show()
                    initialData()
                }
            }
        })




        adapterSimple = AdapterSimple(inputList)
        inputRv.itemAnimator = DefaultItemAnimator()
        inputRv.adapter = adapterSimple
        inputRv.layoutManager = LinearLayoutManager(this)

        initialData()

    }

    private fun initialData() {
        for (i in 1..20) {
            inputList.add(i.toString())
        }

        adapterSimple.notifyItemInserted(adapterSimple.itemCount.minus(1))
    }


}