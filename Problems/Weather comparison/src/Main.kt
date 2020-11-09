import kotlin.math.min

class City(val name: String) {
    var degrees: Int = 0
        set(value) {
            if (value < -92 || value > 57) {
                when (this.name) {
                    "Dubai" -> field = 30
                    "Hanoi" -> field = 20
                    "Moscow" -> field = 5
                }
            }
            else field = value
        }
}        

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    //implement comparing here
    val minTem = minOf(firstCity.degrees, secondCity.degrees, thirdCity.degrees)
    var count = 0
    var minCity = ""
    if (firstCity.degrees == minTem) {
        count++
        minCity = firstCity.name
    }
    if (secondCity.degrees == minTem) {
        count++
        minCity = secondCity.name
    }
    if (thirdCity.degrees == minTem) {
        count++
        minCity = thirdCity.name
    }
    print(if (count > 1) "neither" else minCity)
}