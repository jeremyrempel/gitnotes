package sample

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Person(val fname: String, val lname: String)

@UseExperimental
class JsonTest {

    fun getPerson(): Person {
        //language=JSON
        val input = "{\n  \"fname\": \"Jeremy\",\n  \"lname\": \"Rempel\"\n}"

        return Json.parse(Person.serializer(), input)
    }
}