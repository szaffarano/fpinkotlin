package chapter3.exercises.ex14

import chapter3.List
import chapter3.append
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> concat(lla: List<List<A>>): List<A> =
    foldRight(
        lla,
        List.empty()
    ) { acc, value -> append(acc, value) }

fun <A> concat2(lla: List<List<A>>): List<A> =
    foldLeft(lla, { l: List<A> -> l }) { g, value ->
        { value2 -> g(append(value, value2)) }
    }(List.empty())
// end::init[]

class Exercise14 : WordSpec({
    "list concat" should {
        "concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
