package com.github.jeremyrempel.gitnotes.android.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jeremyrempel.gitnotes.LogLevel
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.log
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ContentsListFragment : Fragment(), ContentsView {

    val user = "jeremyrempel"
    val repo = "gitnotestest"
    private val repoInfo = RepoInfo(user, repo)

    private lateinit var listAdapter: ContentsResponseListAdapter
    private var navigationCallback: NavigationCallback? = null
    private var currentPath: String? = null

    @Inject
    lateinit var actions: ContentsActions

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        loadingView.isGone = !isLoading
        recycler.isGone = isLoading
        content.isGone = isLoading
    }

    companion object {

        const val ARG_PATH = "argpath"

        fun createInstance(path: String? = null): Fragment {
            val b = Bundle()
            b.putString(ARG_PATH, path)

            val frag = ContentsListFragment()
            frag.arguments = b

            return frag
        }
    }

    override fun onUpdate(data: List<ContentsResponseRow>) {
        listAdapter.submitList(data)
    }

    override fun onUpdate(data: ContentsResponseRow) {
        content.text = data.content
    }

    override fun showError(error: Throwable) {
        content.text = error.message
        log(LogLevel.ERROR, this::class.toString(), "Error", error)
    }

    override fun navigateTo(screen: NavScreen) {
        navigationCallback?.navigateTo(screen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        arguments?.let {
            currentPath = it.getString(ARG_PATH)
        }

        listAdapter = ContentsResponseListAdapter(actions::onSelectItem)
    }

    private fun inject() {
        DaggerContentFragmentComponent.builder()
            .contentsPresenterModule(ContentsPresenterModule(this, repoInfo))
            .build()
            .inject(this)
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

        actions.onRequestData(currentPath)
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
}