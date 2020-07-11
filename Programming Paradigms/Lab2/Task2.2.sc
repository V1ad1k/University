def geometric(start: Int, step: Int, count: Int):
List[Int] = {
  if (count < 1) List()
  else if (count == 1) List(start)
  else start :: geometric(start * step, step , count - 1)
}
geometric(1,2,4)