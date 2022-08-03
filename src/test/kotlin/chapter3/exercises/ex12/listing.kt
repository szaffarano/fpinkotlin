package chapter3.exercises.ex12

import chapter3.List
import chapter3.foldLeft
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =
    foldRight(
        xs,
        { b: B -> b },
        { value, acc ->
            { b ->
                acc(f(b, value))
            }
        }
    )(z)

fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B =
    foldLeft(
        xs,
        { b: B -> b },
        { acc, value ->
            { b ->
                acc(f(value, b))
            }
        }
    )(z)

// end::init[]

class Exercise12 : WordSpec({
    "list foldLeftR" should {
        "implement foldLeft functionality using foldRight for nums" {
            foldLeftR(
                List.of(1, 2, 3, 4, 5),
                0
            ) { acc, y ->
                println("FoldLeftR: $y")
                acc + y
            } shouldBe 15
        }
        "implement foldLeft functionality using foldRight for strings" {
            foldLeftR(
                List.of("A", "B", "C", "D"),
                ""
            ) { acc, y -> acc + y } shouldBe "ABCD"
        }
    }

    "list foldRightL" should {
        "implement foldRight functionality using foldLeft for two-elements array" {
            foldRightL(
                List.of(2, 3),
                0
            ) { x, acc -> acc + x } shouldBe 5
        }
        "implement foldRight functionality using foldLeft for two-elements char array" {
            foldRightL(
                List.of("X", "O"),
                ""
            ) { x, acc -> acc + x } shouldBe "OX"
        }
        "implement foldRight functionality using foldLeft for nums" {
            foldRightL(
                List.of(1, 2, 3, 4, 5),
                0
            ) { x, acc -> acc + x } shouldBe 15
        }
        "implement foldRight functionality using foldLeft for strings" {
            foldRightL(
                List.of("A", "B", "C", "D"),
                ""
            ) { x, acc ->
                println("FoldRightLeft: $x")
                acc + x
            } shouldBe "DCBA"
        }
    }
})
