// A comment!
/* Another comment */
/** A documentation comment */
object MyModule {
    def abs(n: Int): Int =
        if (n < 0) -n
        else n

    def factorial(n: Int): Int = {
        // writing a loop with a recursive function
        // If all recursive calls made by a function are in tail position, Scala automatically compiles the recursion to iterative loops
        // annotation is not needed but it will fail the build in case tail recursion is not possible
        @annotation.tailrec
        def loop(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else loop(n - 1, n * acc)

        loop(n, 1)
    }

    private def formatAbs(x: Int) =
        formatResult("absolute", x, abs)

    private def formatFactorial(n: Int) =
        formatResult("factorial", n, factorial)

    private def formatResult(name: String, n: Int, f: Int => Int) = {
        val msg = "The %s of %d is %d."
        msg.format(name, n, f(n))
    }

    def main(args: Array[String]): Unit = {
        println(formatAbs(-42))
        // any method name can be used infix like ` + ` (omitting the dot and parentheses) when calling it with a single argument
        println(MyModule abs -1)
        println(formatFactorial(5))
    }
}