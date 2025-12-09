import java.io.File

fun main() {
    println(part1("year2025/input/day01_test.txt"))
    println(part1("year2025/input/day01.txt"))
    println(part2("year2025/input/day01_test.txt"))
    println(part2("year2025/input/day01.txt"))
}

fun part1(fileName: String): Int{
    var dial = 50
    var code = 0
    File(fileName).forEachLine {
        if (it[0] == 'L'){
            dial = (dial - it.substring(1).toInt()).mod(100)
            if (dial == 0) code++
        }else {
            dial = (dial + it.substring(1).toInt()).mod(100)
            if (dial == 0) code++
        }
    }
    return code
}

fun part2(fileName: String): Int{
    var dial = 50
    var code = 0
    File(fileName).forEachLine { line ->
        val dir = line[0]
        val amount = line.substring(1).toInt()
        val start = dial

        if (dir == 'R') {
            val clicks = (start + amount) / 100
            dial = (start + amount) % 100
            code += clicks
        } else {
            val clicks = if (start == 0) {
                amount / 100
            } else {
                if (amount < start) 0 else 1 + (amount - start) / 100
            }
            dial = ((start - amount) % 100 + 100) % 100
            code += clicks
        }
    }
    return code
}