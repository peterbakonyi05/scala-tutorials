object Polymorhpy {

  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (ordered(as(n), as(n - 1))) false
      else loop(n + 1)

    loop(0)
  }

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
    (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    a => f(g(a))

  def main(args: Array[String]): Unit = {
    println(findFirst(Array(1, 2, 3, 4), (x: Int) => x == 2))

    // (a, b) => a < b is a syntactic sugar for object creation:
    val biggerThan = new Function2[Int, Int, Boolean] {
      // Scala has a special rule for `apply`
      def apply(a: Int, b: Int) = a < b
    }

    val biggerThan3 = partial1(3, biggerThan);
    println(findFirst(Array(1, 2, 4, 5), biggerThan3))
  }
}
