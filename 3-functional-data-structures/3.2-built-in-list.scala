object BuiltInListDemo {
    def main(args: Array[String]) = {
        println("BuiltinListDemo")
        // make a list element by element (`::` is like Cons, but this way there is no need to nest)
        val when = "AM" :: "PM" :: Nil
        println(when)

        val days = List("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        days match {
            case firstDay :: otherDays => println("First day of the week is: " + firstDay)
            case Nil => println("There don't seem to be any week days.")
        }
    }
}