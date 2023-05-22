object Main extends App {

  object MyObject {

    def method(): Int = {
      val x = 2 * 5
      x + 3
    }

  }

  println(MyObject.method())

  val add=(x:Int,y:Int,z:Int)=>x+y+z
  println(add(2,3,7))


  /*

  ex. 3
  In Java we use loop, which iterates through every element of an array and adds up all of them.
  On the other hand, in Scala we use recursive approach, which creates many memory frames of
  a function that sums up element. Frames are created after calling the same function inside
  itself, which are nested in the memory. Each call of the function has access to some part of
  the list, as each time we pass as an argument tail of a list, that is all elements except the first one.
  Unfortunately, recursion uses a lot of memory, which can cause a stack overwflow.

  ex. 4
  The type is Int.
  The result is 13.

  ex. 5
  The type is Int.
  The result is 12.

  */

}