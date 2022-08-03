package chapter3.exercises.ex9

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
    }
// end::init[]

class Exercise9 : WordSpec({
    "list foldLeft" should {
        """apply a function f providing a zero accumulator from tail
            recursive position""" {
            foldLeft(
                List.of(1, 2, 3, 4, 5),
                0
            ) { x, y -> x + y } shouldBe 15
        }

        """concatenate in the proper direction""" {
            foldLeft(List.of("A", "B", "C", "D"), "") { a, b ->
                a + b
            } shouldBe "ABCD"
        }
    }
})
