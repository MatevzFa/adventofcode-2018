import java.io.File
import kotlin.system.measureTimeMillis

class Claim(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int) {
    companion object {

        operator fun invoke(string: String): Claim {

            val re = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".toRegex()

            val (id, left, top, width, height) = re.find(string)!!.destructured

            return Claim(id.toInt(), left.toInt(), top.toInt(), width.toInt(), height.toInt())
        }
    }

    val indices: List<Pair<Int, Int>> by lazy {
        (left until left + width).flatMap {
            val cur = it
            (top until top + height).map { Pair(cur, it) }
        }
    }

    fun contains(pair: Pair<Int, Int>): Boolean {
        return left <= pair.first && pair.first < left + width && top <= pair.second && pair.second < top + height
    }

    fun intersects(other: Claim): Boolean {
        return if (left < other.left)
            left + width > other.left &&
                    if (top < other.top)
                        top + height > other.top
                    else
                        other.top + other.height > top
        else
            other.left + other.width > left &&
                    if (top < other.top)
                        top + height > other.top
                    else
                        other.top + other.height > top
    }

    override fun toString(): String {
        return "#${id} @ ${top},${left}: ${width}x${height}"
    }
}

fun main() {
    val input = File("input").readLines().map { Claim(it) }

    val t1 = measureTimeMillis {
        println(partOne(input))
    }

    println("Part 1 done in ${t1}ms")

    val t2 = measureTimeMillis {
        println(partTwo(input))
    }

    println("Part 2 done in ${t2}ms")
}

fun partOne(input: List<Claim>): Int {
    return input
        .flatMap { it.indices }
        .groupingBy { it }
        .eachCount()
        .values
        .count { it >= 2 }
}

fun partTwo(input: List<Claim>): Int {

    val noOverlaps = input.map { claim ->
        Pair(
                claim.id,
                input
                    .filter { it.id != claim.id }
                    .map { claim.intersects(it) }.all { !it }
        )
    }.filter { (_, bool) -> bool }.map { (id, _) -> id }

    if (noOverlaps.size != 1)
        throw Error("There should only be one claim without intersections, but there are ${noOverlaps.size}")

    return noOverlaps.first()
}
