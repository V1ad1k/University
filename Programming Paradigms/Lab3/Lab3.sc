import scala.annotation.tailrec

def sum(list:List[Int]):Int={
  if (!list.isEmpty)
  {
    list.head+sum(list.tail)
  }
  else
  {
    0
  }
}
def substructTailSum(list:List[Int])={
  sum(list.tail) - list.head
}
substructTailSum(List(1, 2))
substructTailSum(List(4, 2,3))
substructTailSum(List(1, 2,6,8))

def reverseList(list: List[Any]): List[Any] = {
  @tailrec
  def reverseIt(res: List[Any], remainder: List[Any]): List[Any] = {
    if (remainder.isEmpty)res
    else reverseIt(remainder.head::res, remainder.tail)
  }
  reverseIt(List(), list)
}
reverseList(List(1, 2))
reverseList(List(1, 2,5,7))
def combine(list1:List[Int], list2:List[Int]):List[Int]={
  if (list1.isEmpty) list2
  else if (list2.isEmpty) list1
  else List(list1.head,list2.head) ++ combine(list1.tail,list2.tail)
}
combine(List(1,3,5), List(2,4,6))
combine(List(1,2,3), List(4,5,6))
combine(List(3,4,5), List(2,3,1))

