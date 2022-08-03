package chapter3.exercises.ex13

import chapter3.Cons
import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> append(a1: List<A>, a2: List<A>): List<A> =
    foldRight(a1, a2) { value, acc -> Cons(value, acc) }
// end::init[]

fun <A> appendL(a1: List<A>, a2: List<A>): List<A> =
    foldLeft(a1, { l: List<A> -> l }) { f, value ->
        { acc -> f(Cons(value, acc)) }
    }(a2)

class Exercise13 : WordSpec({
    "list append" should {
        "append two lists to each other using foldRight" {
            append(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }

    "list appendL" should {
        "append two lists to each other using foldLeft" {
            appendL(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
