import scala.util.Random

object CustomerID {
  def apply(name: String) = s"$name--${Random.nextLong}"

  // unapply takes an object and tries to give back the arguments
  def unapply(CustomerID: String): Option[String] = {
    val stringArray: Array[String] = CustomerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }
}

object ExtractorObjectsDemo {

  def main(args: Array[String]) {
    val customer1ID = CustomerID("Sukyoung") // shorthand for calling `CustomerID.apply`
    customer1ID match {
      case CustomerID(name) => println(name)
      case _                => println("Could not extract a CustomerID")
    }

    val customer2ID = CustomerID("Peti")
    val CustomerID(name) = customer2ID
    println(name)
  }

}
