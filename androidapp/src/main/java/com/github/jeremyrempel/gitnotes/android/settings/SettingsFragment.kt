package com.github.jeremyrempel.gitnotes.android.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.jeremyrempel.gitnotes.android.NavigationCallback
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.settings.SettingsPresenter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

@SuppressLint("ValidFragment")
class SettingsFragment @Inject constructor(
    private val presenter: SettingsPresenter
) : Fragment(R.layout.fragment_settings) {

    private var navigationCallback: NavigationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()
        registerListeners()

        // we could do this automatically when listener registered but less magic
        presenter.requestData()
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

        presenter.dataCallback = null
        presenter.navCallback = null
    }

    /**
     * Stop listening, avoid delayed clicks occurring after stop
     */
    private fun registerListeners() {
        view?.let { view ->
            view.findViewById<MaterialButton>(R.id.btn_update)?.apply {
                setOnClickListener(::onClickSaveButton)
            }

            view.findViewById<MaterialButton>(R.id.btn_reset).apply {
                setOnClickListener(::onClickResetButton)
            }
        }

        presenter.dataCallback = {
            val editRepoTxt = view?.findViewById<TextInputEditText>(R.id.edit_repo_txt)
            val editRepoUserTxt = view?.findViewById<TextInputEditText>(R.id.edit_repo_username_txt)

            editRepoTxt?.setText(it.repo)
            editRepoUserTxt?.setText(it.user)
        }
        presenter.navCallback = { navigateTo(it) }
    }

    private fun onClickResetButton(buttonView: View) = presenter.onReset()

    private fun onClickSaveButton(buttonView: View) {

        val view = requireView()

        val repoNameTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_txt).text.toString()
        val editRepoUserTxt = view.findViewById<TextInputEditText>(R.id.edit_repo_username_txt).text.toString()

        presenter.onSave(RepoInfo(editRepoUserTxt, repoNameTxt))
    }

    private fun navigateTo(navScreen: NavScreen) = navigationCallback?.navigateTo(navScreen)

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