object TupleDemo {
  def main(args: Array[String]) = {
    val ingredient = ("Sugar", 25)
    println(ingredient._1)
    // taking apart using patter matching
    val (name, quantity) = ingredient
    println(name)
    println(quantity)

    val planets = List(
      ("Mercury", 57.9),
      ("Venus", 108.2),
      ("Earth", 149.6),
      ("Mars", 227.9),
      ("Jupiter", 778.3)
    )
    // another example for pattern matching
    planets.foreach {
      case ("Earth", distance) =>
        println(s"Our planet is $distance million kilometers from the sun")
      case _ =>
    }

    val numPairs = List((2, 5), (3, -7), (20, 56))
    for ((a, b) <- numPairs) {
      println(a * b)
    }

    // note: Case classes have named elements that can improve the readability of some kinds of code
    case class Planet(name: String, distance: Double)
    val planet1 = Planet("Venus", 108.2)
  }
}
