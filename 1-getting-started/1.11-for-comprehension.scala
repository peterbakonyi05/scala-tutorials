case class User(name: String, age: Int)

object ForComprehensionDemo {

  def foo(n: Int, v: Int) =
    for (i <- 0 until n; j <- 0 until n if i + j == v)
      yield (i, j)

  def main(args: Array[String]) = {
    val userBase = List(
      User("Travis", 28),
      User("Kelly", 33),
      User("Jennifer", 44),
      User("Dennis", 23)
    )

    // for + yield creates a List, `user <- userBase` is our generator
    val twentySomethings =
      for (user <- userBase if (user.age >= 20 && user.age < 30))
        yield user.name // i.e. add this to a list

    twentySomethings.foreach(name => println(name))

    foo(10, 10) foreach {
      case (i, j) =>
        println(s"($i, $j) ") // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5) (6, 4) (7, 3) (8, 2) (9, 1)
    }
  }

}
