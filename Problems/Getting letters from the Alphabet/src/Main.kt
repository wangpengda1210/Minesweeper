import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
    val letter = input.next().first()
    
    for (char in 'a'..'z') {
        if (char == letter) return
        print(char)
    }
}