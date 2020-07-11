//task 1 functional
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

def sep(list: List[Int]): (List[Int], List[Int]) = {
  @tailrec
  def Helper(list: List[Int], even: List[Int], odd: List[Int]): (List[Int], List[Int]) = {
    if (list.isEmpty) (even, odd)
    else
    if (list.indexOf(list.last) % 2 == 0) Helper(list.tail, even :+ list.head, odd)
    else Helper(list.tail, even, odd :+ list.head)
  }
  Helper(list, List.empty[Int], List.empty[Int])
}
sep(List(5,6,3,2,1))
sep(List(15,16,13,12,11))
sep(List(1,2,3,4,5,6,7,8,9,0))

//imperative
def sepImp(list: List[Int]): (List[Int], List[Int]) = {
  var even = new ListBuffer[Int]
  var odd = new ListBuffer[Int]
  for (int <- list) {
    if (list.indexOf(int) % 2 == 0) even += int
    else odd += int
  }
  (even.toList, odd.toList)
}
sepImp(List(5,6,3,2,1))
sepImp(List(15,16,13,12,11))
sepImp(List(1,2,3,4,5,6,7,8,9,0))

//task 2
def toPower(n: Int): Stream[Int] = {
  n#::toPower(n*n)
}
val test = toPower(0)
val test2 = toPower(1)
val test3 = toPower(3)
val test4 = toPower(5)
val test5 = toPower(7)
test.take(7).print()
test2.take(7).print()
test3.take(6).print()
test4.take(5).print()
test5.take(5).print()