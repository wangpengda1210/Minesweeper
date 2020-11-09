import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
    val rowList = mutableListOf<Int>()
    val columnList = mutableListOf<Int>()
    
    rowList.add(input.nextInt())
    columnList.add(input.nextInt())
    rowList.add(input.nextInt())
    columnList.add(input.nextInt())
    rowList.add(input.nextInt())
    columnList.add(input.nextInt())
    
    var rowResult = ""
    for (row in 1..5) {
        if (row !in rowList) rowResult += "$row "
    }
    
    var columnResult = ""
    for (col in 1..5) {
        if (col !in columnList) columnResult += "$col "
    }
    
    println(rowResult.trim())
    println(columnResult.trim())
}