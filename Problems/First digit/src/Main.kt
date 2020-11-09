fun main() {
    // put your code here
    val word = readLine()
    for (char in word!!) {
        if (char.isDigit()) {
            print(char)
            break
        }
    }
}