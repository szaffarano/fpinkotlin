package chapter3.exercises.ex19

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
    foldRight(xa, List.empty()) { y, ys ->
        foldRight(f(y), ys) { a, bs -> Cons(a, bs) }
    }
// end::init[]

class Exercise19 : WordSpec({
    "list flatmap" should {
        "map and flatten a list" {
            val xs = List.of(1, 2, 3)
            flatMap(xs) { i -> List.of(i, i) } shouldBe
                List.of(1, 1, 2, 2, 3, 3)
        }
    }
})
