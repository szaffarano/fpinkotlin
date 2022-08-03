package chapter3.exercises.ex3

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> drop(l: List<A>, n: Int): List<A> =
    when {
        n == 0 -> l
        n > 0 -> when (l) {
            is Cons -> drop(l.tail, n - 1)
            is Nil -> throw IllegalStateException("Can not drop a Nil")
        }
        else -> throw IllegalStateException("Can not drop negative values")
    }
// end::init[]

class Exercise3 : WordSpec({
    "list drop" should {
        "drop a given number of elements within capacity" {
            drop(List.of(1, 2, 3, 4, 5), 3) shouldBe
                List.of(4, 5)
        }

        "throw exception when trying to drop negative number of elements" {
            shouldThrow<IllegalStateException> {
                drop(List.of(1, 2, 3, 4, 5), -3)
            }
        }

        "drop a given number of elements up to capacity" {
            drop(List.of(1, 2, 3, 4, 5), 5) shouldBe Nil
        }

        """throw an illegal state exception when dropped elements
            exceed capacity""" {
            shouldThrow<IllegalStateException> {
                drop(List.of(1, 2, 3, 4, 5), 6)
            }
        }
    }
})
