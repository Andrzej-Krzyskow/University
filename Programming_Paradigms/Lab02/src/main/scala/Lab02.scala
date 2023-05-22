import javax.swing.JToolBar.Separator
import scala.annotation.tailrec

object Lab02 {


  def sumList(list: List[Int]): Int = {

    @tailrec
    def tailRec(list: List[Int], acc: Int = 0): Int = list match {
      case Nil => acc
      case h :: t if h % 2 == 0 => tailRec(t, acc)
      case h :: t => tailRec(t, acc + h)

    }

    if (list == null) 0
    else tailRec(list = list)
  }



  def connectStrings(listOfString: List[String], separator: String): String = {

    @tailrec
    def tailConnect(acc: String, list: List[String], separator: String): String = list match{
      case Nil=> acc.dropRight(separator.length)
      case h::t =>tailConnect(acc + h + separator, t, separator)
    }

    if (listOfString == null) ""
    else tailConnect("", listOfString, separator)
  }

  def countIntOccurrences(list: List[Int], int: Int): Int = {

    @tailrec
    def tailCount(counter: Int, list: List[Int], element: Int): Int = list match{
      case Nil=>counter
      case h::t if h.equals(element)  =>tailCount(counter + 1, t, element)
      case _::t => tailCount(counter,t,element)

    }

    if (list == null) 0
    else tailCount(0, list, int)
  }

  def fibonacci(n: Int): Int = {

    @tailrec
    def tailFibonacci(n: Int, next: Int, current: Int): Int = {
      if (n <= 0) current
      else tailFibonacci(n - 1, next + current, next)
    }

    if (n < 0) -1
    else tailFibonacci(n, 1, 0)
  }

  def main(args: Array[String]): Unit = {

    //ex. 1
    println("Task 1")
    val list1 = List(0, 1, 2, 3, 4, 5, 6, 7)
    val list2 = List(3, 3, 3, 3, 2, 2, 2, 2, 9)
    val list3 = null
    val list4 = List(-1, -3)
    println("First sumList: " + sumList(list1))
    println("Second sumList: " + sumList(list2))
    println("Third sumList: " + sumList(list3))
    println("Fourth sumList: " + sumList(list4))

    //ex. 2
    println("\nTask 2")
    val listS1 = List("Hi", "I am", "Andy")
    val listS2 = List("Who", "Are", "you", "Stranger")
    val listS3 = List("I", "am", "a", "creepy", "programmer", "bruh", "123")
    val listS4 = List()

    println("First String: " + connectStrings(listS1, " "))
    println("FirstAgain String: " + connectStrings(listS1, "----"))
    println("Second String: " + connectStrings(listS2, ","))
    println("Third String: " + connectStrings(listS3, "101"))
    println("Fourth String: " + connectStrings(listS4, "----"))

    //ex. 3
    println("\nTask 3")
    val listC1 = List(0, 0, -1, -1, 1, 12, 3, 2, 1, 2, 1) // should be 3
    val listC2 = List() // should be 0
    val listC3 = null // should be 0
    val listC4 = List(-5, -1, -3, -4, -5, 5, 5, 5, -5, -5) // should be 4


    println("First count: " + countIntOccurrences(listC1, 1))
    println("First again count: " + countIntOccurrences(listC1, 2))
    println("Second count: " + countIntOccurrences(listC2, 2))
    println("Third count: " + countIntOccurrences(listC3, -8))
    println("Fourth count: " + countIntOccurrences(listC4, -5))


    //ex. 4
    println("\nTask 4")
    println("Fibonacci -1: " + fibonacci(-1))
    println("Fibonacci 0: " + fibonacci(0))
    println("Fibonacci 1: " + fibonacci(1))
    println("Fibonacci 2: " + fibonacci(2))
    println("Fibonacci 3: " + fibonacci(3))
    println("Fibonacci 4: " + fibonacci(4))
    println("Fibonacci 5: " + fibonacci(5))
    println("Fibonacci 10: " + fibonacci(10))


  }

}
