package com.github.jeremyrempel.gitnotes.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.android.di.DaggerSingletonComponent
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.properties.Delegates

class ContentsListFragment : Fragment(), ContentsView {

    val user = "jeremyrempel"
    val repo = "gitnotestest"

    private lateinit var listAdapter: ContentsResponseListAdapter

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        loadingView.isGone = !isLoading
    }

    private val actions: ContentsActions by lazy {
        val dagger = DaggerSingletonComponent.create()
        val service = dagger.gitHubApi()
        val view: ContentsView = this
        val coroutineContext = dagger.coroutineContext()

        val repoInfo = RepoInfo(user, repo)

        ContentsPresenter(coroutineContext, view, service, repoInfo)
    }

    override fun onUpdate(data: List<ContentsResponse>) {
        listAdapter.submitList(data)
    }

    override fun showError(error: Throwable) {
        view?.findViewById<TextView>(R.id.text)?.text = error.message
        Log.e("SampleAndroid", error.message, error)
    }

    override fun navigateTo() {
        Toast.makeText(context, "Navigate to", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter = ContentsResponseListAdapter(actions::onClick)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = listAdapter

            val dividerItemDecoration = DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
        }

        actions.onRequestData()
    }
}