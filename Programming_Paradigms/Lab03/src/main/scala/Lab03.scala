import scala.annotation.tailrec

object Lab03 extends App {

  // ex. 1
  def sumList(list: List[Int]): Int = {

    @tailrec
    def tailRec(acc: Int, list: List[Int]): Int = {
      if (list.isEmpty) acc
      else tailRec(acc + list.head, list.tail)
    }

    if (list == null) 0
    else tailRec(0, list)
  }

  // ex. 2
  // a)
  def reverseListBad[A](list: List[A]): List[A] = {

    def reverse(list: List[A]): List[A] = {
      if (list.tail == Nil) list
      else reverse(list.tail) :+ list.head
    }

    if (list == null) null
    else if (list == Nil) Nil
    else reverse(list)
  }

  // b)
  def reverseListGood[A](list: List[A]): List[A] = {

    @tailrec
    def reverse(list: List[A], result: List[A] = Nil): List[A] = list match {
      case Nil => result
      case h :: t => reverse(t, h :: result)
    }

    if (list == null) null
    else if (list == Nil) Nil
    else reverse(list)
  }

  // ex. 3
  def alternateTwoLists[A](list1: List[A], list2: List[A]): List[A] = {

    @tailrec
    def tailRec(list1: List[A], list2: List[A], result: List[A] = Nil): List[A] = list1 match {
      case Nil if list2 != Nil => tailRec(Nil, list2.tail, result :+ list2.head)
      case h :: t if list2 == Nil => tailRec(t, Nil, result :+ h)
      case h :: t if list2 != Nil => tailRec(t, list2.tail, result :+ h :+ list2.head)
      case _ => result
    }

    if (list1 == null) list2
    else if (list2 == null) list1
    else tailRec(list1, list2)
  }

  // ex. 4 - on tutorials I had the same code :) --> tailRec is much better as it doesn't create unnecessary memory frames
  def fibonacci(n: Int): BigInt = {

    @tailrec
    def tailFibonacci(n: Int, next: BigInt, current: BigInt): BigInt = {
      if (n <= 0) current
      else tailFibonacci(n - 1, next + current, next)
    }

    if (n < 0) -1
    else tailFibonacci(n, 1, 0)
  }

  // ex. 5
  def splitList(list: List[Int]): List[List[Int]] = {

    @tailrec
    def tailRec(list: List[Int], result: List[List[Int]] = List(Nil, Nil)): List[List[Int]] = list match {
      case Nil => result
      case h :: t => if (h % 2 != 0) tailRec(t, List(result.head :+ h, result(1))) else tailRec(t, List(result.head, result(1) :+ h))
    }

    if (list == null) null
    else tailRec(list)
  }

  // ex. 6
  def checkList(list: List[Int]): Boolean = {

    if (list == null) return false
    if (list == Nil) return true

    @tailrec
    def tailCheck(list: List[Int], prev: Int): Boolean = {
      if (list.isEmpty) true
      else if (list.head < prev) false
      else tailCheck(list.tail, list.head)
    }

    tailCheck(list.tail, list.head)
  }

  // ex. 7
  def replaceNth[A](xs: List[A], n: Int, x: A): List[A] = {


    @tailrec
    def tailRec[B](list: List[B], n: Int, x: B, result: List[B] = Nil): List[B] = {
      if (list == Nil) result
      else if (n == 0) tailRec(list.tail, -1, x, result :+ x)
      else tailRec(list.tail, n - 1, x, result :+ list.head)
    }

    if (xs == null) null
    else if (n >= xs.size || n < 0) xs
    else tailRec(xs, n, x)
  }


  // ex. 8

  //<editor-fold desc="code for ex. 8">

  def fromAtmTo_(atm: Double)(multiplier: Double): Double = {
    atm * multiplier
  }

  def fromAtmToPSI(atm: Double): Double = {
    fromAtmTo_(atm)(14.6956)
  }

  def fromAtmToTorr(atm: Double): Double = {
    fromAtmTo_(atm)(760)
  }

  def fromAtmToBar(atm: Double): Double = {
    fromAtmTo_(atm)(1.01325)
  }
  //</editor-fold>

  val list1 = List(0, 1, 2, 3, 4, 5, 6, 7)
  val list2 = List(11, 12, 13, 14, 15, 16, 17, 18)
  val list3 = null
  val list4 = List(-1, -3)
  val list5 = Nil

  //<editor-fold desc="Task1">
  println("Task 1")
  println("First sumList: " + sumList(list1))
  println("Second sumList: " + sumList(list2))
  println("Third sumList: " + sumList(list3))
  println("Fourth sumList: " + sumList(list4))
  println("Fifth sumList: " + sumList(list5))
  //</editor-fold>

  //<editor-fold desc="Task2">
  println("\nTask 2.a")
  println(reverseListBad(list1))
  println(reverseListBad(list2))
  println(reverseListBad(list3))
  println(reverseListBad(list4))
  println(reverseListBad(list5))

  println("\nTask 2.b")
  println(reverseListGood(list1))
  println(reverseListGood(list2))
  println(reverseListGood(list3))
  println(reverseListGood(list4))
  println(reverseListBad(list5))
  //</editor-fold>

  //<editor-fold desc="Task3">
  println("\nTask 3")
  println(alternateTwoLists(list1, list2))
  println(alternateTwoLists(list1, list3))
  println(alternateTwoLists(list3, list3))
  println(alternateTwoLists(list4, list1))
  println(alternateTwoLists(list5, list3))
  println(alternateTwoLists(list2, list4))
  //</editor-fold>

  //<editor-fold desc="Task4">
  println("\nTask 4")
  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(10))
  //</editor-fold>

  //<editor-fold desc="Task5">
  println("\nTask 5")
  println(splitList(list1))
  println(splitList(list2))
  println(splitList(list3))
  println(splitList(list4))
  println(splitList(list5))


  //</editor-fold>

  //<editor-fold desc="Task6">
  println("\nTask 6")
  println(checkList(list1))
  println(checkList(list2))
  println(checkList(list3))
  println(checkList(list4))
  println(checkList(list5))
  //</editor-fold>

  //<editor-fold desc="Task7">
  println("\nTask7")
  println(replaceNth(list1, 18, 20))
  println(replaceNth(list2, 0, 20))
  println(replaceNth(list3, 2, 20))
  println(replaceNth(list4, 3, 20))
  println(replaceNth(list5, 5, 20))
  //</editor-fold>

  //<editor-fold desc="Task8">
  println("\nTask8")
  println(f"${fromAtmToPSI(10)}%12.5f")
  println(f"${fromAtmToBar(10)}%12.5f")
  println(f"${fromAtmToTorr(10)}%12.5f")
  //</editor-fold>

}
