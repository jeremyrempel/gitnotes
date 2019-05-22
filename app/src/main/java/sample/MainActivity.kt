package sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.jeremyrempel.gitnotes.api.*
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import com.github.jeremyrempel.gitnotes.presentation.CoroutinePresenter
import io.ktor.client.HttpClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), ContentsView {

    val user = "jeremyrempel"
    val repo = "gitnotestest"

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        if (isLoading) {
            loadingView.visibility = View.VISIBLE
            text.visibility = View.GONE
        } else {
            loadingView.visibility = View.GONE
            text.visibility = View.VISIBLE
        }
    }

    val coroutineContext = Dispatchers.Main

    // todo inject
    private val actions: ContentsActions by lazy {
        val service = getService(user, repo)
        val view: ContentsView = this

        getActions(coroutineContext, view, service)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actions.onRequestData()
    }

    override fun onUpdate(data: List<ContentsResponse>) {
        text.text = data.map { it.name + "\n" }.reduce { acc, s -> acc + s }
    }

    override fun showError(error: Throwable) {
        findViewById<TextView>(R.id.text).text = error.message
        Log.e("SampleAndroid", error.message, error)
    }
}