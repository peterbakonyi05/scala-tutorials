/**
  * class Foo[+A] // A covariant class
  * class Bar[-A] // A contravariant class
  * class Baz[A]  // An invariant class
  *
  * Generic classes in Scala are invariant by default. This means that they are neither covariant nor contravariant.
  */
/**
  * COVARIANCE
  * For two types A and B where A is a subtype of B, then List[A] is a subtype of List[B]
  */
abstract class Animal {
  def name: String
}
case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal

/**
  * CONTRAVARIANCE
  * Just the opposite. Making A contravariant implies that for two types A and B
  * where A is a subtype of B, Writer[B] is a subtype of Writer[A]
  */
abstract class Printer[-A] {
  def print(value: A): Unit
}

class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit =
    println("The cat's name is: " + cat.name)
}

// DEMO
object CovarianceTest {
  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  def printMyCat(printer: Printer[Cat]): Unit = {
    printer.print(myCat)
  }

  def main(args: Array[String]) = {
    // covariance example
    val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
    val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))
    printAnimalNames(cats)
    printAnimalNames(dogs)

    // contravariance example
    val myCat: Cat = Cat("Boots")
    printMyCat(new CatPrinter)
    printMyCat(new AnimalPrinter)
  }
}
