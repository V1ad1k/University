import java.lang.reflect.Field

trait debug {
  def debugName : String = "Class " + this.getClass.getName
  def debugVars : Unit = {
    this.getClass.getDeclaredFields.foreach(x =>  {
      x.setAccessible(true)
      println("Field " + x.getName + " => " + x.getType + " " + x.get(this) + "\n")
    })  }
}

class Point(xv: Int, yv: Int) extends debug {
  var x = xv
  var y = yv
  var name: String = "Test"
}

var p = new Point(3, 4)
p.debugName
p.debugVars