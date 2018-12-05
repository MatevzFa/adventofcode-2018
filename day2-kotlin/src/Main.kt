import java.io.File

fun main() {
    val input = File("input").readLines()

    println(partOne(input))
    println(partTwo(input))
}

fun partOne(input: List<String>): Int {
    fun Boolean.toInt() = if (this) 1 else 0

    return input.map {
        it.toCharArray()
            .groupBy { it }
            .map { it.value.size }
    }.map {
        Pair(it.contains(2).toInt(), it.contains(3).toInt())
    }.fold(Pair(0, 0)) { (hasTwo, hasThree), (nTwos, nThrees) ->
        Pair(nTwos + hasTwo, nThrees + hasThree)
    }.toList().fold(1) { x, a -> x * a }
}

fun partTwo(input: List<String>): String {
    fun Boolean.toInt() = if (this) 1 else 0

    return input.flatMap {
        val cur = it
        input.map { Pair(cur, it) }
    }.filter { (a, b) -> a < b }.map { (a, b) ->
        Pair(
            Pair(a, b),
            a.toCharArray().zip(b.toCharArray()).map { (a, b) ->
                ((a - b) != 0).toInt()
            }.sum()
        )
    }.filter { (_, count) -> count == 1 }.map { (p, _) ->
        p.first.toCharArray().zip(p.second.toCharArray())
    }.first()
        .filter { (a, b) -> a == b }
        .map { (a, _) -> a }
        .joinToString(separator = "")
}