package sample

import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.test.Test
import kotlin.test.assertTrue

data class WorkerDataClass(val name: String, val desc: String)

class SampleTestsIOS {
    @Test
    fun `test worker`() {
        val worker = Worker.start()
        val job = worker.execute(TransferMode.SAFE, {
            "Hello World"
        }, {
            WorkerDataClass("Result from worker", it)
        })

        job.consume {
            println(it)
        }

        worker.requestTermination(true)
    }
}