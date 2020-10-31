package com.surelabs.indonesia.koreancornersreaders

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surelabs.e.jsoupbotapps.model.DataItemPost
import com.surelabs.indonesia.koreancornersreaders.adapter.AdapterSimple
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var isLinear = false
    private var dataItem: MutableList<DataItemPost?>? = mutableListOf()
    private lateinit var adapterSimple: AdapterSimple
    private var position = 0
    private lateinit var listViewType: ArrayList<Int>
    private lateinit var vm: MainViewModel
    var countLoadMore by Delegates.notNull<Int>()
    var isLoading by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        isLoading = false
        listViewType = ArrayList()
        countLoadMore = 0

        vm.getTransaksi(countLoadMore)
        vm.res.observe(this) {
            dataItem?.removeAll(it.data!!)
            dataItem?.addAll(it.data!!)
            adapterSimple = AdapterSimple(dataItem) {
                Browser(this@MainActivity, it?.slugTitle)
            }
            inputRv.layoutManager = GridLayoutManager(this, 2)
            inputRv.adapter = adapterSimple
        }

        inputRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lms = recyclerView.layoutManager
                var lastVisiblePosition = 0
                when (lms) {
                    is LinearLayoutManager -> {
                        lastVisiblePosition = lms.findLastCompletelyVisibleItemPosition()
                        position = lms.findFirstVisibleItemPosition()
                    }
                    is GridLayoutManager -> {
                        lastVisiblePosition = lms.findLastCompletelyVisibleItemPosition()
                        position = lms.findFirstVisibleItemPosition()
                    }
                }
                val countItem = lms?.itemCount

                val isLastPosition = countItem?.minus(1) == lastVisiblePosition
                if (!isLoading && isLastPosition) {
                    isLoading = true
                    countLoadMore += 1
                    vm.getTransaksi(countLoadMore)
                    vm.res.observe(this@MainActivity) {
                        dataItem?.removeAll(it.data!!)
                        dataItem?.addAll(it.data!!)
                        recyclerView.post {
                            adapterSimple.refresh(listData = dataItem)
                            isLoading = false
                        }
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "Change Layout")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
                if (!isLinear) {
                    val lm = LinearLayoutManager(this)
                    inputRv.layoutManager = lm
                    inputRv.scrollToPosition(position)
                    isLinear = true
                } else {
                    val lm = GridLayoutManager(this, 2)
                    inputRv.layoutManager = lm
                    inputRv.scrollToPosition(position)
                    isLinear = false
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
