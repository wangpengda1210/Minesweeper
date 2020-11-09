import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    print(findTriples(scanner))
}

fun findTriples(scanner: Scanner): Int {
    val count = scanner.nextInt()
    
    if (count < 3) return 0
    var first = Int.MIN_VALUE
    var second = Int.MIN_VALUE
    var third = Int.MIN_VALUE
    var tripleCount = 0
    
    for (i in 1..count) {
        first = second
        second = third
        third = scanner.nextInt()
        
        if (second == first + 1 && third == second + 1) tripleCount++
    }
    
    return tripleCount
}
