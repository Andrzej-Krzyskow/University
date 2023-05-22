import java.lang.foreign.Addressable
import scala.collection.mutable.ArrayBuffer

object Main extends App {

  sealed trait Topping

  case object Cheese extends Topping

  case object Pepperoni extends Topping

  case object Sausage extends Topping

  case object Mushrooms extends Topping

  case object Onions extends Topping


  sealed trait CrustSize

  case object SmallCrustSize extends CrustSize

  case object MediumCrustSize extends CrustSize

  case object LargeCrustSize extends CrustSize


  sealed trait CrustType

  case object RegularCrustType extends CrustType

  case object ThinCrustType extends CrustType

  case object ThickCrustType extends CrustType


  trait ToppingsManager {
    def addTopping(t: Topping): Unit

    def removeTopping(t: Topping): Unit

    def removeAllToppings(): Unit
  }

  class Pizza(var crustSize: CrustSize, var crustType: CrustType, val toppings: ArrayBuffer[Topping]) extends ToppingsManager {
    def addTopping(t: Topping): Unit = toppings += t

    def removeTopping(t: Topping): Unit = toppings -= t

    def removeAllToppings(): Unit = toppings.clear()

  }

  class Order(var pizzas: ArrayBuffer[Pizza], var customer: Customer)

  // ex. 1

  class Customer(val name: String, var phoneNumber: Int, var address: Address)

  class Address(var country: String, var city: String, var zipCode: Int, var street: String, var flatNo: Int)

  // ex. 2

  def countPizzaPrice(pizza: Pizza): Int = {
    var price: Int = 0

    pizza.crustSize match
      case SmallCrustSize => price += 10
      case MediumCrustSize => price += 20
      case LargeCrustSize => price += 30

    pizza.crustType match
      case ThinCrustType => price -= price / 4
      case ThickCrustType => price += price / 4
      case RegularCrustType => price = price

    for (t <- pizza.toppings) {
      t match
        case Pepperoni | Sausage => price += 5
        case Cheese => price += 4
        case Mushrooms | Onions => price += 3
    }

    price
  }

  // ex. 3

  trait Animal {

    val name: String

    var age: Int

    def speak(): Unit

    def eat(): Unit

    def jump(): Unit
  }

  class Dog(val name: String, var age: Int) extends Animal {

    override def speak(): Unit = println("Bark, bark!")

    override def eat(): Unit = println("Omnomnomnom tasty")

    override def jump(): Unit = println("I'm so high")
  }

  val roger = Dog("Roger", 10)


  println(roger.name)
  println(roger.age)
  roger.eat()
  roger.speak()
  roger.jump()


  // ex. 4

  /*  class Time(private var _hour: Int) {

      if (_hour < 0) {
        _hour = 0
      }

      def hour: Int = _hour

      def hour_=(newHour: Int): Unit = {
        if (newHour < 0) {
          _hour = 0
        } else {
          _hour = newHour
        }
      }

    }*/

  class Time(private var hour: Int) {

    if (hour < 0) {
      hour = 0
    }

    def getHour: Int = hour

    def setHour(newHour: Int): Unit = {
      if (newHour < 0) {
        hour = 0
      } else {
        hour = newHour
      }
    }

  }

  object Time {

    def apply(hours: Int): Time = {
      val t = new Time(hours)
      if (hours < 0) {
        t.hour = 0
      }
      t
    }
  }

  // ex. 5

  object Square {
    val a = 2

    def printPerimiter: Unit = println(4 * a)

    def printArea: Unit = println(a * a)
  }

  // ex. 6
  class Author(val name: String, val email: String, val gender: Char)

  class Book(val name: String, val author: Author, var price: Double, var qty: Int = 0) {  // this line covers both constructors - with and without quantity

    if (price < 0) {
      price = 0
    }

    def getName = name

    def getAuthor = author

    def getPrice = price

    def getQty = qty

    def setPrice(price: Double) = {
      if (price > 0) {
        this.price = price
      } else {
        this.price = 0
      }
    }

    def setQty(qty: Int) = {
      if (qty > 0) {
        this.qty = qty
      } else {
        this.qty = 0
      }
    }

    override def toString = s"Book($name, $author, $price, $qty)"
  }

  val bookWithoutQty = Book("book", Author("name", "email", 'M'), 2)
  val bookWithQty = Book("book", Author("name", "email", 'M'), 2,10)


}
