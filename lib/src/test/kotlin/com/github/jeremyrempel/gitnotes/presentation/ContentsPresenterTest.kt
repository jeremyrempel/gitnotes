import com.github.jeremyrempel.gitnotes.api.mock.Fakes
import com.github.jeremyrempel.gitnotes.api.GithubApiFakeError
import com.github.jeremyrempel.gitnotes.api.GithubApiFake
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import io.mockk.spyk
import io.mockk.verifySequence
import kotlin.test.Test
import kotlinx.coroutines.test.TestCoroutineScope
import kotlin.test.Ignore

class ContentsPresenterTest {

    private val view = spyk<ContentsView>()
    private val testContext = TestCoroutineScope().coroutineContext

    @Test
    @Ignore("Update to not rely on mockk")
    fun `onRequest data updates view`() {
        val presenter = ContentsPresenter(
            testContext,
            GithubApiFake(),
            Fakes.Settings
        )
        presenter.view = view

        presenter.onRequestData()

        verifySequence {
            view setProperty "isUpdating" value true
            view.onUpdate(GithubApiFake().data.data)
            view setProperty "isUpdating" value false
        }
    }

    @Test
    @Ignore("Update to not rely on mockk")
    fun `on request data error update view`() {
        val presenter = ContentsPresenter(
            testContext,
            GithubApiFakeError(),
            Fakes.Settings
        )
        presenter.view = view

        presenter.onRequestData()

        verifySequence {
            view setProperty "isUpdating" value true
            view setProperty "isUpdating" value false
//            view.onError(any())
        }
    }
}
