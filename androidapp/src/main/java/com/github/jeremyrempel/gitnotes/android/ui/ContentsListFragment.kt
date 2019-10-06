package com.github.jeremyrempel.gitnotes.android.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.android.NavigationCallback
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.android.vm.ContentsViewModel
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("ValidFragment")
class ContentsListFragment @Inject constructor(
    private val vmFactory: ViewModelProvider.Factory
) : Fragment() {

    private lateinit var listAdapter: ContentsResponseListAdapter
    private var navigationCallback: NavigationCallback? = null
    private val viewModel: ContentsViewModel by viewModels { vmFactory }

    private var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        loadingView.isGone = !isLoading
        recycler.isGone = isLoading
        content.isGone = isLoading
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var currentPath: String? = null
        arguments?.let {
            currentPath = it.getString(ARG_PATH)
        }

        listAdapter = ContentsResponseListAdapter(viewModel::onSelectItem)
        viewModel.requestData(currentPath)
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

        listenForChanges(viewModel)
    }

    private fun listenForChanges(viewModel: ContentsViewModel) {
        viewModel.getData().observe(this, Observer { onUpdate(it) })
        viewModel.isError().observe(this, Observer { onError(it) })
        viewModel.isLoading().observe(this, Observer { isUpdating = it })
        viewModel.navEvent().observe(this, Observer { navigateTo(it) })
    }

    private fun onUpdate(data: ContentsUi) {
        when (data) {
            is ContentsUi.Multiple -> listAdapter.submitList(data.result)
            is ContentsUi.Single -> content.text = data.result.content
        }
    }

    private fun onError(error: String) {
        content.text = error
    }

    private fun navigateTo(screen: NavScreen) {
        navigationCallback?.navigateTo(screen)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavigationCallback) {
            navigationCallback = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationCallback = null
    }

    companion object {
        const val ARG_PATH = "argpath"
    }
}
