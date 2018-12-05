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

    fun indices(): List<Pair<Int, Int>> {
        return (left until left + width).flatMap {
            val cur = it
            (top until top + height).map { Pair(cur, it) }
        }
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
            .flatMap { it.indices() }
            .groupingBy { it }
            .eachCount()
            .values
            .count { it >= 2 }
}

fun partTwo(input: List<Claim>): String {
    throw NotImplementedError();
}