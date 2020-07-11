def func(list1: Stream[Int], list2: Stream[Int]): Stream[Int] = {
  if (list1.isEmpty) list2
  else if (list2.isEmpty) list1
  else list1.head #:: list2.head #:: func(list1.tail, list2.tail)
}

val test1 = func(Stream(1, 2), Stream(0, 3, 1))
val test2 = func(Stream(1, 2, 3), Stream(0, 3, 1, 4))
val test3 = func(Stream(3, 2, 1), Stream(0, 3, 1, 4, 5, 6, 7))
test1.take(5).print()
test2.take(7).print()
test3.take(10).print()

val Streaminfinity1 = Stream.from(1)
val Streaminfinity2 = Stream.from(125)

val test4 = func(Streaminfinity1, Streaminfinity2)
test4.take(5).print()

val Streaminfinity3 = Stream.from(10)
val Streaminfinity4 = Stream.from(225)

val test5 = func(Streaminfinity3, Streaminfinity4)
test5.take(5).print()

val Streaminfinity5 = Stream.from(999)

val test6 = func(Streaminfinity1, Streaminfinity5)
test6.take(10).print()


