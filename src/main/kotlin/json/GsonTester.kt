import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class User(var name:String, var id:Int = 0,
                var address: Address, var age:Int,
                var toys: List<Toy>? = null
) {
    override fun equals(other: Any?) =
        other is User && other.id == this.id
}

data class Address(var country:String,var city:String) {
}

data class Toy(var name:String, var type:ToyType)

enum class ToyType {
    CarToy, PlaneToy, ShipToy
}

var gson = Gson(); // val gson = GsonBuilder().setPrettyPrinting().create()
var the_json = """
        {
          "name": "jack",
          "id": 1,
          "address": {
            "country": "China",
            "city": "nanjing"
          },
          "age": 12,
          "toys": [
            {
              "name": "car1",
              "type": "CarToy"
            },
            {
              "name": "plane",
              "type": "PlaneToy"
            }
          ]
        }
    """.trimMargin()

var the_json_array = """
        [{
          "name": "jack",
          "id": 1,
          "address": {
            "country": "China",
            "city": "BeiJing"
          },
          "age": 12,
          "toys": [
            {
              "name": "car1",
              "type": "CarToy"
            },
            {
              "name": "plane",
              "type": "PlaneToy"
            }
          ]
        },
        {
          "name": "mike",
          "id": 1,
          "address": {
            "country": "China",
            "city": "nanjing"
          },
          "age": 13,
          "toys": [
            {
              "name": "car2",
              "type": "CarToy"
            }
          ]
        }]
    """.trimMargin()
fun generateJson() {
    var address = Address("China","BeiJing")
    var toys = listOf(Toy("car1",ToyType.CarToy),Toy("plane",ToyType.PlaneToy))
    var user = User("jack",1,address,12,toys)

    println("user $user created ok ")


    println(gson.toJson(user))
}

fun parseUserJson(json:String):User? {
    if(json!=null) {
        return gson.fromJson(json, User::class.java)
    }
    return null
}

fun parseUserJsonAray(jsonArray:String):List<User>? {
    if(jsonArray!=null) {
        val typeToken = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonArray, typeToken)
    }
    return null
}

fun main() {
//    generateJson()
//    val user = parseUserJson(the_json)
//    println("ParsedJson User is $user") // got this: ParsedJson User is User(name=jack, id=1, address=Address(country=China, city=nanjing), age=12, toys=[Toy(name=car1, type=CarToy), Toy(name=plane, type=PlaneToy)])
    val users = parseUserJsonAray(the_json_array)
    println("parsed users: $users")
}