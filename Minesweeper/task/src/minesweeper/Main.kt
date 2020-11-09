package minesweeper

import java.util.*

val cells = mutableListOf<MutableList<Cell>>()
lateinit var minePositions: List<Int>
var mineCount: Int = 0
val markedPositions = mutableListOf<Int>()
var hasLose = false
var hasInit = false

fun main() {
    
    val scanner = Scanner(System.`in`)
    init(scanner)
    printCurrentStatus()
    
    while (!hasWin() && !hasLose) {
        print("Set/unset mine marks or claim a cell as free:")
        
        val markY = scanner.nextInt() - 1
        val markX = scanner.nextInt() - 1
        val action = scanner.next()
        val markCell = cells[markX][markY]
        val markPosition = indexToPosition(markCell.positionX, markCell.positionY)
        
        if (action == "mine") {
            when (markCell.mark) {
                "." -> {
                    markedPositions.add(markPosition)
                    markCell.mark = "*"
                }
                "*" -> {
                    markedPositions.remove(markPosition)
                    markCell.mark = "."
                }
                else -> println("There is a number here!")
            }
        } else if (action == "free") {
            if (!hasInit) {
                while (markPosition in minePositions) minePositions = (0..80).shuffled().take(mineCount)
                hasInit = true
            }
            
            if (markPosition in minePositions) {
                
                hasLose = true
                for (i in 0..8) {
                    for (j in 0..8) {
                        if (indexToPosition(i, j) in minePositions) cells[i][j].mark = "X"
                    }
                }
                
            }
            else markCell.exploreCell()
        }
        
        printCurrentStatus()
    }
    
    if (hasWin()) println("Congratulations! You found all the mines!")
    if (hasLose) println("You stepped on a mine and failed!")
    
}

fun init(scanner: Scanner) {
    
    print("How many mines do you want on the field?")
    mineCount = scanner.nextInt()
    minePositions = (0..80).shuffled().take(mineCount)
    
    for (i in 0..8) {
        val row = mutableListOf<Cell>()
        for (j in 0..8) {
            row.add(Cell(i, j))
        }
        cells.add(row)
    }
    
}

fun printCurrentStatus() {
    println("\n |123456789|")
    println("-|---------|")
    for (i in 0..8) {
        print("${i + 1}|")
        for (j in 0..8) {
            print(cells[i][j].mark)
        }
        println("|")
    }
    println("-|---------|")
}

fun indexToPosition(i: Int, j: Int) = (i * 8 + j) + i

fun hasWin(): Boolean {
    if (minePositions.all { it in markedPositions }) return true
    
    var freeCount = 0
    for (row in cells) {
        for (cell in row) {
            if (cell.mark == "." || cell.mark == "*") freeCount++
        }
    }

    return freeCount == minePositions.size
}

fun Cell.findCellAroundOrNull(direction: Direction): Cell? = when (direction) {
    Direction.LEFT -> if (positionY == 0) null else cells[positionX][positionY - 1]
    Direction.RIGHT -> if (positionY == cells.lastIndex) null else cells[positionX][positionY + 1]
    Direction.UP -> if (positionX == 0) null else cells[positionX - 1][positionY]
    Direction.DOWN -> if (positionX == cells.lastIndex) null else cells[positionX + 1][positionY]
}

fun Cell.findMinesAround(): Int {
    
    var count = 0
    val cellsAround = getCellsAround()
    
    for (cell in cellsAround) {
        if (cell != null && (cell.positionX * 8 + cell.positionY) + cell.positionX in minePositions) count++
    }
    
    return count
    
}

fun Cell.exploreCell() {
    
    val minesAround = findMinesAround()
    
    if (minesAround > 0) mark = minesAround.toString()
    else {
        mark = "/"
        val cellsAround = getCellsAround()
        for (cell in cellsAround) {
            if (cell?.mark == ".") cell.exploreCell()
            if (cell?.mark == "*" && indexToPosition(cell.positionX, cell.positionY) !in minePositions) cell.exploreCell()
        }
    }
    
}

fun Cell.getCellsAround() = listOf(findCellAroundOrNull(Direction.UP)?.findCellAroundOrNull(Direction.LEFT),
        findCellAroundOrNull(Direction.UP)?.findCellAroundOrNull(Direction.RIGHT),
        findCellAroundOrNull(Direction.DOWN)?.findCellAroundOrNull(Direction.LEFT),
        findCellAroundOrNull(Direction.DOWN)?.findCellAroundOrNull(Direction.RIGHT),
        findCellAroundOrNull(Direction.UP), findCellAroundOrNull(Direction.DOWN),
        findCellAroundOrNull(Direction.LEFT), findCellAroundOrNull(Direction.RIGHT))

enum class Direction() {
    LEFT, RIGHT, UP, DOWN
}

data class Cell(val positionX: Int, val positionY: Int, var mark: String = ".")


