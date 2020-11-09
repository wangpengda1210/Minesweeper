import java.util.*

fun main() {
    // put your code here
    val scanner = Scanner(System.`in`)
    val word = scanner.next()
    var result = 0
    
    for (char in word) {
        var count = 0
        for (charForCount in word) {
            if (charForCount == char) count++
            if (count == 2) break
        }
        if (count == 1) result++
    }
    
    print(result)
}