/**
  * Case classes are like regular classes with a few key differences:
  *  - have an apply method by default which takes care of object construction (no need for `new`)
  *  - when you create a case class with parameters, the parameters are public
  *  - case classes are compared by structure and not by reference
  *  - good for modeling immutable data and pattern matching
  */
case class Book(isbn: String)

object CaseClassDemo {
  def main(args: Array[String]) {
    val frankenstein = Book("978-0486282114")
    val frankenstein2 = Book("978-0486282114")
    println("Compared by structure and not ref: " + (frankenstein == frankenstein2))
  }
}
