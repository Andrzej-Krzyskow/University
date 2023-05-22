import scala.annotation.tailrec

object lab05 extends App {

  // ex. 1
  def lrepeat[A](k: Int)(lxs: LazyList[A]): LazyList[A] = lxs.flatMap(x => LazyList.fill(k)(x))

  // ex. 2
  lazy val fibList: LazyList[Int] = 0 #:: 1 #:: fibList.zip(fibList.tail).map(n => n._1 + n._2)

  def fib(n: Int): Int = {
    if (n < 0) -1
    else fibList(n)
  }

  // ex. 3
  trait lBT[+A]

  case object LEmpty extends lBT[Nothing]

  case class LNode[+A](elem: A, left: () => lBT[A], right: () => lBT[A]) extends lBT[A]


  def treeGen(n: BigInt): LNode[BigInt] = {

    def genNode(n: BigInt)() : LNode[BigInt] = LNode(n, genNode(2*n), genNode(2*n+1))

    genNode(n)()
  }

  // ex. 1
  println("\nfirst")
  val naturals: LazyList[Int] = 1 #:: naturals.map(_ + 1)
  val test = lrepeat(3)(naturals)

  test.take(5).foreach(println)
  println("--------")
  test.take(6).foreach(println)
  println("--------")
  test.take(7).foreach(println)

  // ex. 2
  println("\nsecond")
  println(fib(0))
  println(fib(1))
  println(fib(2))
  println(fib(3))
  println(fib(4))
  println(fib(4))
  println(fib(5))

  // ex. 3
  println("\nthird")
  val node: LNode[BigInt] = treeGen(3)
  val node1: LNode[BigInt] = node.left().asInstanceOf[LNode[BigInt]]
  val node2: LNode[BigInt] = node.right().asInstanceOf[LNode[BigInt]]
  val node11: LNode[BigInt] = node1.left().asInstanceOf[LNode[BigInt]]
  val node12: LNode[BigInt] = node1.right().asInstanceOf[LNode[BigInt]]
  val node21: LNode[BigInt] = node2.left().asInstanceOf[LNode[BigInt]]
  val node22: LNode[BigInt] = node2.right().asInstanceOf[LNode[BigInt]]
  val node212: LNode[BigInt] = node21.right().asInstanceOf[LNode[BigInt]]
  val node2121: LNode[BigInt] = node212.left().asInstanceOf[LNode[BigInt]]
  println(node.elem)
  println(node1.elem)
  println(node2.elem)
  println(node11.elem)
  println(node12.elem)
  println(node21.elem)
  println(node22.elem)
  println(node212.elem)
  println(node2121.elem)




}
