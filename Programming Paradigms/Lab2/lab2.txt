def fun(number :(Int, Int)): (Int,Int) =
{
  (number._1+number._2,(number._1-number._2))
}
fun (1,2)
fun (3,-1)
fun (5,2)
def geometric(start: Int, step: Int, count: Int):
List[Int] = {
  if (count < 1) List()
  else if (count == 1) List(start)
  else start :: geometric(start * step, step , count - 1)
}
geometric(1,2,4)
geometric(2,2,4)
def stat(list: List[String]): Int = {
  if (list.isEmpty) 0
  else if (list.head.length > 3) 1 + stat(list.tail)
  else 0 + stat(list.tail)
}
stat(List("This", "is", "String"))
stat(List("this", "is ", "fine", "today"))