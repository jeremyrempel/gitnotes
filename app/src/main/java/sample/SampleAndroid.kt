package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.GithubService
import com.github.jeremyrempel.gitnotes.api.apiHost
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import com.github.jeremyrempel.gitnotes.api.httpClient
import com.github.jeremyrempel.gitnotes.presentation.ReadmeActions
import com.github.jeremyrempel.gitnotes.presentation.ReadmePresenter
import com.github.jeremyrempel.gitnotes.presentation.ReadmeView
import kotlinx.coroutines.Dispatchers
import java.nio.charset.StandardCharsets
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), ReadmeView {

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        if (isLoading) {
//            progressBar.visibility = View.VISIBLE
//            button.visibility = View.GONE
//            imageView.visibility = View.GONE
//            text.visibility = View.GONE
        } else {
//            progressBar.visibility = View.GONE
//            button.visibility = View.VISIBLE
//            imageView.visibility = View.VISIBLE
//            text.visibility = View.VISIBLE
        }
    }

    private val client = httpClient
    private val api: GithubApi by lazy {
        GithubService(httpClient, apiHost, "jeremyrempel", "gitnotestest")
    }

    private val actions: ReadmeActions by lazy {
        ReadmePresenter(Dispatchers.Main, this, api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actions.onRequestData()
    }

    override fun onUpdate(data: ReadMeResponse) {
        val result = Base64.decode(data.content, Base64.DEFAULT)
        val strResult = java.lang.String(result, "UTF-8")

        findViewById<TextView>(R.id.text).text = strResult
    }

    override fun showError(error: Throwable) {
        findViewById<TextView>(R.id.text).text = error.message
        Log.e("SampleAndroid", error.message)
    }
}