/**
  * SIMPLE EXAMPLE
  */
abstract class A {
  val message: String
}
class B extends A {
  val message = "I'm an instance of class B"
}
trait C extends A {
  def loudMessage = message.toUpperCase()
}
// classes can have on supper class (`extends`) but main mixins (`with`)
class D extends B with C

/**
  * MORE INTERESTING EXAMPLE
  */
abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next(): T
}

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext = i < s.length
  def next() = {
    val ch = s charAt i
    i += 1
    ch
  }
}

// it is a trait so it does not need to implement the abstract members of `AbsIterator`
trait RichIterator extends AbsIterator {
  def forEach(f: T => Unit): Unit = while (hasNext) f(next)
}

class RichStringIterator(s: String) extends StringIterator(s) with RichIterator {

}

object MixinsDemo {

  def main(args: Array[String]) {
    val d = new D
    println(d.message)
    println(d.loudMessage)

    val a = new RichStringIterator("SCALA")
    a forEach println
  }

}
