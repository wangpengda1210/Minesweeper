import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
    val word = input.next()
    
    for (char in 'a'..'z') {
        if (char in word) continue
        print(char)
    }
}