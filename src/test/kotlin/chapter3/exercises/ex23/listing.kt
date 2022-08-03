package chapter3.exercises.ex23

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::startsWith[]
tailrec fun <A> startsWith(l1: List<A>, l2: List<A>): Boolean =
    when {
        l2 is Nil -> true
        l1 is Cons && l2 is Cons -> {
            when (l1.head) {
                l2.head -> startsWith(l1.tail, l2.tail)
                else -> false
            }
        }
        else -> false
    }
// end::startsWith[]

// tag::init[]
tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>): Boolean =
    when {
        startsWith(xs, sub) -> true
        xs is Cons -> hasSubsequence(xs.tail, sub)
        else -> false
    }
// end::init[]

class Exercise23 : WordSpec({
    "list subsequence" should {
        "determine if a list starts with" {
            val xs = List.of(1, 2, 3)
            startsWith(xs, List.of(1)) shouldBe true
            startsWith(xs, List.of(1, 2)) shouldBe true
            startsWith(xs, xs) shouldBe true
            startsWith(xs, List.of(2, 3)) shouldBe false
            startsWith(xs, List.of(3)) shouldBe false
            startsWith(xs, List.of(6)) shouldBe false
        }

        "identify subsequences of a list" {
            val xs = List.of(1, 2, 3, 4, 5)
            hasSubsequence(xs, List.of(1)) shouldBe true
            hasSubsequence(xs, List.of(1, 2)) shouldBe true
            hasSubsequence(xs, List.of(2, 3)) shouldBe true
            hasSubsequence(xs, List.of(3, 4)) shouldBe true
            hasSubsequence(xs, List.of(3, 4, 5)) shouldBe true
            hasSubsequence(xs, List.of(4)) shouldBe true

            hasSubsequence(xs, List.of(1, 4)) shouldBe false
            hasSubsequence(xs, List.of(1, 3)) shouldBe false
            hasSubsequence(xs, List.of(2, 4)) shouldBe false
        }
    }
})
