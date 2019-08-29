class Point(var x: Int = 0, var y: Int = 0) {
    println("This is the constructor");

    def move(dx: Int, dy: Int): Unit = {
        x = x + dx;
        y = y + dy;
    }

    override def toString: String = s"($x, $y)"

}

// with getter / setter syntax
class Circle {
    private var _center = new Point
    private var _radius = 0

    def center = _center
    def center_= (newCenter: Point): Unit = {
        _center = newCenter
    }

    def radius = _radius
    def radius_= (newRadius: Int): Unit = {
      if(newRadius > 0) _radius = newRadius else printWarning
    }

    private def printWarning = println("WARNING: Invalid value")

    override def toString: String = s"Center: $center, radius: $radius"
}

object ClassesDemo {
    def main(args: Array[String]) = {
        val p1 = new Point;
        val p2 = new Point(1, 2);
        val p3 = new Point(2);
        val p4 = new Point(y = 2);
        println(p4);
        val c1 = new Circle
        c1.radius = 5
        c1.center = p3
        println(c1)

    }
}