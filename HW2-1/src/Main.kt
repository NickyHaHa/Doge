import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Scanner(System.`in`)
        while (input.hasNext()) {
            val num = input.nextInt()
            println(
                    "Q1 : " +
                            isFactor(num).stream()
                                    .collect(Collectors.toList())
            )
            println(
                    "Q2 : " +
                            isFactor(num).stream()
                                    .filter { obj: Int -> isPrime(obj) }
                                    .collect(Collectors.toList())
            )
            //存質因數的List
            val onlyprime: MutableList<Int> = ArrayList()
            isFactor(num).stream()
                    .filter { obj: Int -> isPrime(obj) }
                    .forEach { i: Int -> onlyprime.add(i) }
            //計算質因數出現幾次的List,先使得兩者size相同
            val match: MutableList<Int> = ArrayList()
            onlyprime.stream()
                    .forEach { i: Int -> match.add(i) }
            //計算(recursive)
            divideByPrime(num, onlyprime, 0, match, 0)
            print("Q3 : [")
            IntStream.rangeClosed(0, onlyprime.size - 1)
                    .forEach { i: Int -> print("( " + onlyprime[i] + ", " + match[i] + " )") }
            print("]")
            println()
        }
    }

    //Find factors and put them in List, then return this List
    fun isFactor(N: Int): List<Int> {
        val factors: MutableList<Int> = ArrayList()
        IntStream.rangeClosed(1, N)
                .filter { i: Int -> N % i == 0 }
                .forEach { i: Int -> factors.add(i) }
        return factors
    }

    //Find primes using sqrt to show TRUE or FALSE
    fun isPrime(N: Int): Boolean {
        val root = Math.sqrt(N.toDouble()).toInt()
        return if (N == 1) false else IntStream.rangeClosed(2, root)
                .noneMatch { i: Int -> N % i == 0 }
    }

    fun divideByPrime(N: Int, P: List<Int>, now: Int, M: MutableList<Int>, count: Int): List<Int> { //第now個元素(質因數)
        var N = N
        var now = now
        var count = count
        val T = P[now]
        //當原數被除到小於1，回傳計算完成的List
        return if (N <= 1) M else if (N % T == 0) {
            N /= T
            count++
            //計算的List第now個元素值設為count
            M[now] = count
            divideByPrime(N, P, now, M, count)
        } else {
            now++
            count = 0
            divideByPrime(N, P, now, M, count)
        }
    }
}