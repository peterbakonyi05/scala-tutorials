package fpinscala.datastructures

// `trait` is an abstract interface that may optionally contain implementations of some methods
// `sealed` means that all implementations of the trait must be declared in this file
sealed trait List[+A]
case object Nil extends List[Nothing]
// the + in front signals that A is a covariant or “positive” parameter of List
// This means that, for instance, List[Dog] is considered a subtype of List[Animal], assuming Dog is a subtype of Animal
case class Cons[+A](head: A, tail: List[A]) extends List[A]

// companion object: just an object with the same name as the data type
object List {

  // this is not stack-safe
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    // pattern matching (if multiple patterns match the target, Scala chooses the first matching case)
    as match {
      case Nil         => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  @annotation.tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B =
    as match {
      case Nil        => z
      case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }

  def sumOldImplementation(ints: List[Int]): Int = ints match {
    case Nil         => 0
    case Cons(x, xs) => x + sumOldImplementation(xs) // recursive implementation
  }

  def sum(ints: List[Int]): Int = foldRight(ints, 0)((i, sum) => i + sum)

  def productOldImplementation(ds: List[Double]): Double = ds match {
    case Nil          => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs)  => x * productOldImplementation(xs)
  }

  // `_ * _`: useful shorthand in cases where the function parameters are mentioned just once in the body of the function
  // Each underscore introduces a new (unnamed) function parameter and references it
  def product(ints: List[Int]): Int = foldRight(ints, 1)(_ * _)

  /**
    * Remove the first element of a `List`.
    */
  def tail[A](list: List[A]): List[A] = list match {
    case Nil        => sys.error("tail of empty list")
    case Cons(_, t) => t
  }

  /**
    * Remove the first element of a `List`.
    */
  def setHead[A](list: List[A], h: A): List[A] = list match {
    case Nil        => sys.error("cannot set head of empty list")
    case Cons(_, t) => Cons(h, t)
  }

  def drop[A](list: List[A], n: Int): List[A] = {
    if (n <= 0) list
    else
      list match {
        case Nil        => Nil
        case Cons(_, t) => drop(t, n - 1)
      }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    // the syntax is to add `if <cond>` after the pattern, before the `=>`, where `<cond>` can use any of the variables introduced by the pattern
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _                  => l
  }

  // only copies values until the first list is exhausted
  // in this case immutable linked list is much more efficient than an array!
  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil        => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  // using `foldRight`
  def length[A](l: List[A]): Int =
    foldRight(l, 0)((_, acc) => acc + 1)

  // using `foldLeft`
  def length2[A](l: List[A]): Int =
    foldLeft(l, 0)((acc, _) => acc + 1)

  // `apply` is a  variadic function: it accepts zero or more arguments of type A
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    // the special _* type annotation allows us to pass a Seq to a variadic method
    else Cons(as.head, apply(as.tail: _*))

}

object ListDemo extends App {
  println("Pattern matching");
  // `_` matches any expression, we could use any other expression, but usually `_` indicates a variable whose value we ignore
  println(List(1, 2, 3) match {
    case _ => 42
  })

  // returns the head, `1` in this case
  println(List(1, 2, 3) match {
    case Cons(h, _) => h
  })

  println(List(1, 2, 3) match {
    case Cons(_, t) => t
  })

  // this would result a runtime `MatchError`
  // List(1,2,3) match { case Nil => 42 }

  // excercise 3.1: what is the result?
  val x1 = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _)))          => x
    case Nil                                   => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t)                            => h + List.sum(t)
    case _                                     => 101
  }
  println(x1);
}
