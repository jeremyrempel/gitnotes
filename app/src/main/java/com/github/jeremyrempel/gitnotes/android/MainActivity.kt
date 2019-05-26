package com.github.jeremyrempel.gitnotes.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.api.*
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_list.*
import kotlinx.coroutines.Dispatchers
import sample.R
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), ContentsView {

    val user = "jeremyrempel"
    val repo = "gitnotestest"

    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = ListAdapter {
            Toast.makeText(applicationContext, "Click: $it", Toast.LENGTH_SHORT).show()
        }

        findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)

            val data = listOf(
                ContentsResponse(
                    "name",
                    "FILE",
                    100,
                    "http://api.github.com/blah"
                )
            )

            adapter = listAdapter
        }

        actions.onRequestData()
    }

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        if (isLoading) {
            loadingView.visibility = View.VISIBLE
//            text1.visibility = View.GONE
        } else {
            loadingView.visibility = View.GONE
//            text1.visibility = View.VISIBLE
        }
    }

    val coroutineContext = Dispatchers.Main

    // todo inject
    private val actions: ContentsActions by lazy {
        val service = getService(user, repo)
        val view: ContentsView = this

        getActions(coroutineContext, view, service)
    }


    override fun onUpdate(data: List<ContentsResponse>) {
        listAdapter.submitList(data)
//        text.text = data.map { it.name + "\n" }.reduce { acc, s -> acc + s }
    }

    override fun showError(error: Throwable) {
        findViewById<TextView>(R.id.text).text = error.message
        Log.e("SampleAndroid", error.message, error)
    }
}