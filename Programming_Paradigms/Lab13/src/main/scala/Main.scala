import scala.language.reflectiveCalls

object Main extends App {
  def quacker(duck: {def quack(value: String): String}) {
    println(duck.quack("Quack"))
  }


  // ex. 1
  def makeSound[A <: {def makeNoise(): Unit}](obj: A): Unit = {
    obj.makeNoise()
  }

  def s(x: {def makeNoise(): Unit}): Unit = {
    x.makeNoise()
  }


  class Dog {
    def makeNoise() {
      println("woof")
    }
  }

  class Cat {
    def makeNoise() {
      println("meow")
    }
  }

  s(new Dog)
  s(new Cat)
  makeSound(new Dog)
  makeSound(new Cat)

  // ex. 2

  type Method = {
    def method(input: String): String
  }

  class Foo {
    def method(input: String): String = input
  }

  class Bar {
    def method(input: String): String = input
  }

  val m1: Method = new Bar()
  val m2: Method = new Foo()

  println(m1.method("test1"))
  println(m2.method("test2"))


  // ex. 3
  case class Person(firstName: String, lastName: String) extends Ordered[Person] {

    override def compare(that: Person): Int = {
      val lastNameComp = this.lastName compare that.lastName

      if (lastNameComp != 0) lastNameComp
      else this.firstName compare that.firstName
    }
  }

  val p1 = Person("John", "Doe")
  val p2 = Person("William", "Doe")
  val p3 = Person("Anna", "Doe")
  val p4 = Person("Andrew", "Abrams")

  var personList: List[Person] = List(p1, p2, p3, p4)
  println(personList)
  println(personList.sorted)


  // ex. 4
  def wordCounter(text: String): scala.collection.mutable.Map[String, Int] = {
    val map = text.split(" ").groupBy(identity).view.mapValues(_.length).toMap
    collection.mutable.Map() ++ map
  }

  val text = "A A A A B A B B BB BB C C CC DD EE E x xx xxx x xx xxx"
  println(wordCounter(text))

  def wordCounter(text: String): Map[String, Int] = {
    val wordCount = Map[String, Int]()
    val words = text.split(" ")
    for (word <- words) {
      wordCount(word) = wordCount.getOrElse(word, 0) + 1
    }
    wordCount
  }
}
