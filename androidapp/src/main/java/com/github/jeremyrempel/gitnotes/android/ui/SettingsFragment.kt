package com.github.jeremyrempel.gitnotes.android.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.gitnotes.Constants
import com.github.jeremyrempel.gitnotes.android.NavigationCallback
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

@SuppressLint("ValidFragment")
class SettingsFragment @Inject constructor(
    private val vmFactory: ViewModelProvider.Factory,
    private val settingsRepo: SettingsRepo
) : Fragment(R.layout.fragment_settings) {

    private var navigationCallback: NavigationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    private val viewModel: ContentsViewModel by viewModels { vmFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)


        val editRepoTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_txt)
        val editRepoUserTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_username_txt)
        settingsRepo.getRepoInfo().apply {
            editRepoTxt.setText(repo)
            editRepoUserTxt.setText(user)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        registerClickListeners()
    }

    override fun onStop() {
        super.onStop()
        unregisterClickListeners()
    }

    private fun unregisterClickListeners() {
        view?.let { view ->
            view.findViewById<MaterialButton>(R.id.btn_update)?.apply {
                setOnClickListener(null)
            }

            view.findViewById<MaterialButton>(R.id.btn_reset).apply {
                setOnClickListener(null)
            }
        }
    }

    private fun registerClickListeners() {
        view?.let { view ->
            view.findViewById<MaterialButton>(R.id.btn_update)?.apply {
                setOnClickListener { onClickSaveButton(view) }
            }

            view.findViewById<MaterialButton>(R.id.btn_reset).apply {
                setOnClickListener { onClickResetButton(view) }
            }
        }
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

    private fun onClickResetButton(view: View) {
        val repoNameTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_txt)
        repoNameTxt.setText(Constants.DEFAULT_REPO)

        val editRepoUserTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_username_txt)
        editRepoUserTxt.setText(Constants.DEFAULT_REPO_USERNAME)
    }

    private fun onClickSaveButton(view: View) {
        val repoNameTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_txt)
        val editRepoUserTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_username_txt)

        settingsRepo.updateRepoName(repoNameTxt.text.toString(), editRepoUserTxt.text.toString())

        navigationCallback?.navigateTo(NavScreen.List("/", false))
    }
}