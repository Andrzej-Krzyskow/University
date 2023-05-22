import scala.annotation.tailrec

object Main extends App {


  //task 1
  def eratosthenesSieve(limit: Int): List[Int] = {

    @tailrec
    def sieveRec(list: List[Int], result: List[Int] = Nil): List[Int] = {
      if (list.isEmpty) result
      else sieveRec(list.tail.filter(x => x % list.head != 0), result :+ list.head)
    }

    sieveRec(List.range(2, limit + 1))
  }

  //task 2
  sealed trait Functions

  case class Add(a: Int, b: Int) extends Functions

  case class Negation(n: Int) extends Functions

  def calculator(function: Functions): Any = function match {
    case Add(a, b) => a + b
    case Negation(n) => -n
    case null => "Not valid function"
  }

  //task 3
  enum EnumFunctions:
    case Add(a: Int, b: Int)
    case Negation(n: Int)

  def enumCalc(enumFunction: EnumFunctions): Any = enumFunction match {
    case EnumFunctions.Add(a: Int, b: Int) => a + b
    case EnumFunctions.Negation(n: Int) => -n
    case null => "No valid function"
  }

  //task 4
  sealed trait Bool

  case object True extends Bool

  case object False extends Bool

  def AND(b1: Bool, b2: Bool): Bool = (b1, b2) match {
    case (True, True) => True
    case _ => False
  }

  def OR(b1: Bool, b2: Bool): Bool = (b1, b2) match {
    case (False, False) => False
    case _ => True
  }

  def XOR(b1: Bool, b2: Bool): Bool = (b1, b2) match {
    case (True, True) | (False, False) => False
    case _ => True
  }

  def NAND(b1: Bool, b2: Bool): Bool = (b1, b2) match {
    case (True, True) => False
    case _ => True
  }

  def NOR(b1: Bool, b2: Bool): Bool = (b1, b2) match {
    case (False, False) => True
    case _ => False
  }

  //task 5
  def printType[A](argument: A): Unit = argument match{
    case _:Int => println("That's Int")
    case _:String => println("That's String")
    case _:Boolean => println("That's Boolean")
    case _:Char => println("That's Char")
    case _:Double => println("That's Double")
  }

  println("First task")
  println(eratosthenesSieve(200))

  println("\nSecond task")
  println(calculator(Add(1, 2)))
  println(calculator(Negation(1)))

  println("\nThird task")
  println(enumCalc(EnumFunctions.Add(1, 2)))
  println(enumCalc(EnumFunctions.Negation(1)))

  println("\nFourth task")
  println(AND(True, False))
  println(XOR(True, False))
  println(NAND(True, False))
  println(OR(True, False))
  println(NOR(True, False))


  println("\nFifth task")
  printType("String")
  printType(12)
  printType('s')
  printType(true)
  printType(2.3232)




}
