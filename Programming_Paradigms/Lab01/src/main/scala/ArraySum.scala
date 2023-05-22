object ArraySum {

  private def arraySum(list: List[Int]): Int = {

    def tailRec(list: List[Int], sum: Int = 0): Int = list match {
      case Nil => sum
      case h :: t => tailRec(t, sum + h)
    }
    /*
    if (list.isEmpty) {
      0
    } else {
      list.head + arraySum(list.tail)
    }*/
    tailRec(list)
  }


  def main(args: Array[String]): Unit = {

/*    val list = List(1, 2, 3, 4, 5, 6)

    println(arraySum(list))*/
def find[A](xs: List[A], predicate: A => Boolean): Option[A] = {
  xs match {
    case Nil => None
    case head :: tail =>       if (predicate(head)) Some(head) else find(tail, predicate)
  }
}
    //find(List(1, 4, 5, 7, 2, 8), (x => x % 2 == 0))
/*    def currying(s1:String)(s2:String) = s1 + " " + s2
    println(currying("a")("b"))
    val toUncurrying = Function.uncurried(currying _)
    println(toUncurrying("a", "b"))*/

    def returnBanana:String={
      "banana"
    }
    println(returnBanana)
  }
}
