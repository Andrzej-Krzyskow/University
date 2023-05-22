import akka.actor.Actor
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])

  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    message.replyTo ! Greeted(message.whom, context.self)
    Behaviors.same

  }
}

object HelloWorldBot {

  def apply(max: Int): Behavior[HelloWorld.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int, max: Int): Behavior[HelloWorld.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1
      context.log.info2("Greeting no {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! HelloWorld.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
}

object HelloWorldMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(HelloWorld(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(HelloWorldBot(2), message.name)
        greeter ! HelloWorld.Greet(message.name, replyTo)
        Behaviors.same
      }
    }

}

class WordCountActor extends Actor {
  var totalWords = 0

  def receive: PartialFunction[Any, Unit] = {
    case message: String =>
      totalWords += message.split(" ").length
      println(s"word counter I have received: $message and totalWords is $totalWords")
    case msg => println(s"can't understand ${msg.toString}")
  }
}
/*  val wordCountActor = system.actorOf(Props[WordCountActor], "test")
  val anotherOne = system.actorOf(Props[WordCountActor], "test2")

  wordCountActor ! "what is this melody"
  wordCountActor ! "come one baby is"
  anotherOne ! "testing"*/

object Main2 extends App {
  val sys: ActorSystem[HelloWorldMain.SayHello] = ActorSystem(HelloWorldMain(), "hello")
  //sys ! HelloWorldMain.SayHello("World")
  sys ! HelloWorldMain.SayHello("Akka")

  sys.terminate()
}

