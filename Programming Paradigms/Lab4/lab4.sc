import scala.annotation.tailrec

def averagetail(list1: List[Double], list2: List[Double]): List[Double] = {
  @tailrec
  def support(step1: List[Double], step2: List[Double], result: List[Double]): List[Double] = {
    if (step1.isEmpty && step2.isEmpty) result
    else if (step2.isEmpty) result :+ step1.head/2
    else if (step1.isEmpty) result :+ step2.head/2
    else support(step1.tail, step2.tail, result :+ (step1.head +step2.head)/2)
  }
  support(list1, list2, List.empty[Double])
}
averagetail( List(5,4,3,2,3),List (1,2,3,4,5,6))

def average(list1: List[Double], list2: List[Double]): List[Double] = {
  if  (list1.isEmpty && list2.isEmpty) List()
  else if (list2.isEmpty)  list1.head/2 :: average(list1.tail, list2)
  else if (list1.isEmpty) list2.head/2 :: average(list1, list2.tail)
  else List((list1.head + list2.head)/2) ++ average(list1.tail, list2.tail)
}
average( List(5,4,3,2),List (1,2,3,4,5,6))

def createList(tuple: (Int, String), result: List[String]): List[String] = {
  if (tuple._1==0) result.reverse
  else (createList((tuple._1-1, tuple._2), tuple._2::result))
}
def decode(list: List[(Int, String)],sep : String): List[String] = {
  if (list.isEmpty) List()
  else createList(list.head, List()) ++ decode(list.tail,sep)
}
decode(List((2, "a"), (3, "b")), "' '")
decode(List((2, "a"), (3, "b"),(1,"c")),"' '")