object HelloWorld {

  def main(args: Array[String]): Unit = {

    if (args.isEmpty) {
      println("Hello, world Anonym!")
    } else {
      println("Hello, world " + args(0) + "!")
    }

  }
}
