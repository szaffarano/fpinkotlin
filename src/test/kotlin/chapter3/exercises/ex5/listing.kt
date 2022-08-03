package chapter3.exercises.ex5

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

// tag::init[]
// (1, (2, (3, nil)))
// (1, (2, nil))

// (1, (2, nil))
// (1, nil))
fun <A> init(l: List<A>): List<A> {
    return when (l) {
        is Nil -> throw IllegalStateException("Nil not allowed here")
        is Cons -> when (l.tail) {
            is Nil -> Nil
            is Cons -> Cons(l.head, init(l.tail))
        }
    }
}
// end::init[]

class Exercise5 : WordSpec({

    "list init" should {
        "return first element in a two-element size list" {
            init(List.of(1, 2)) shouldBe
                List.of(1)
        }

        "return first two elements in a three-element size list" {
            init(List.of(1, 2, 3)) shouldBe
                List.of(1, 2)
        }

        "return all but the last element" {
            init(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(1, 2, 3, 4)
        }

        "return Nil if only one element exists" {
            init(List.of(1)) shouldBe Nil
        }

        "throw an exception if no elements exist" {
            shouldThrow<IllegalStateException> {
                init(List.empty<Int>())
            }
        }
    }
})
