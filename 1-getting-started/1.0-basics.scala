object BasicsDemo {
    def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier

    def main(args: Array[String]) = {
        println(addThenMultiply(2, 3)(4))
    }
}