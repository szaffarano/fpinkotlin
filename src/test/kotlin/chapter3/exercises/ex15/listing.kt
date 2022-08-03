package chapter3.exercises.ex15

import chapter3.Cons
import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun increment(xs: List<Int>): List<Int> =
    foldRight(
        xs,
        List.empty()
    ) { value, acc -> Cons(value + 1, acc) }
// end::init[]

fun incrementL(xs: List<Int>): List<Int> =
    foldLeft(
        xs,
        { a: List<Int> -> a }
    ) { acc, value ->
        { b -> acc(Cons(value + 1, b)) }
    }(List.empty())

class Exercise15 : WordSpec({
    "list increment" should {
        "add 1 to every element" {
            increment(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(2, 3, 4, 5, 6)

            incrementL(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(2, 3, 4, 5, 6)
        }
    }
})
