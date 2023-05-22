import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.event.Logging

import scala.runtime.Nothing$

object Test extends App {

  case class CapitalCities() extends Actor {
    val log = Logging(context.system, self)

    override def receive: Receive = {
      case "Poland" => log.info("Warsaw")
      case _ => log.info("Unknown")

    }
  }


  val system = ActorSystem("system")
  val cities = system.actorOf(Props(new CapitalCities()))

  cities ! "ASDASD"
  cities ! "Poland"


  case class StartMatch()

  case class Ball(result: Int)

  case class Opponent(ref: ActorRef)

  case class Player(name: String, var opponent: ActorRef) extends Actor {


    override def receive: Receive = {

      case Opponent(ref) => {
        opponent = ref
      }


      case Ball(result) => {
        val hit = Math.random() > 0.5
        if(result>=10) {
          self ! PoisonPill
        } else if (hit) {
          println(s"$name Hit with result $result")
          opponent ! Ball(result)
        } else {
          val newResult = result + 1
          println(s"$name Missed, new result $newResult")
          opponent ! Ball(newResult)
          if (newResult >= 10) {
            println(s"$name END of match with result $newResult")
            self ! PoisonPill
          }
        }

      }

    }
  }

  case class Match(system: ActorSystem) extends Actor {
    override def receive: Receive = {

      case StartMatch => {
        val p1 =system.actorOf(Props(new Player("p1", null)))
        val p2 =system.actorOf(Props(new Player("p2", p1)))

        p1 ! Opponent(p2)
        p1 ! Ball(0)

      }

    }
  }

  val pingPongActor = system.actorOf(Props(new Match(system)))
  pingPongActor ! StartMatch
  system.terminate()


}
