import akka.actor.typed.Behavior
import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.event.LoggingAdapter

import scala.concurrent.Await
//import akka.actor.typed.{ActorRef,ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.event.Logging


object Main extends App {


  /// *** FOR TASK 1 AND 2 I USED akka.actor.typed AND FOR TASK 3 akka.actor ***


  // ex. 1

  /*  object WelcomeActor {

      final case class WelcomeMessage(name: String)

      def apply(): Behavior[WelcomeMessage] =
        Behaviors.receive { (context, message) =>
          message.name match {
            case "Anna" => context.log.info("Hi Anna")
            case "Tom" => context.log.info("Hello Tom")
            case _ => context.log.info("Hello Stranger")
          }
          Behaviors.same
        }
    }

    val welcomeActor = ActorSystem(WelcomeActor(), "welcomeSystem")
    welcomeActor ! WelcomeActor.WelcomeMessage("Anna")
    welcomeActor ! WelcomeActor.WelcomeMessage("Tom")
    welcomeActor ! WelcomeActor.WelcomeMessage("Annananana")*/

  // ex. 2

  //noinspection NameBooleanParameters
  /*object Player {

    def apply(maxPts: Int): Behavior[Match.Ball] = {

      Behaviors.receive { (context, message) =>
        var newPlayer1Pts = message.player1Pts
        var newPlayer2Pts = message.player2Pts
        context.log.info(message.toString)

        message.from match {
          case "player1" if message.prevPlayerStruck => newPlayer1Pts += 1
          case "player2" if message.prevPlayerStruck => newPlayer2Pts += 1
          case _ =>
        }

        if (newPlayer1Pts >= 10 || newPlayer2Pts >= 10) {
          message.replyTo ! Match.Ball(message.to, message.from, context.self, newPlayer1Pts, newPlayer2Pts, false)
          Behaviors.stopped
        } else {
          val hit: Boolean = Math.random() < 0.5
          message.replyTo ! Match.Ball(message.to, message.from, context.self, newPlayer1Pts, newPlayer2Pts, hit)
          Behaviors.same
        }

      }
    }

  }

  object Match {

    final case class StartMatch()

    final case class Ball(from: String, to: String, replyTo: ActorRef[Ball], player1Pts: Int, player2Pts: Int, prevPlayerStruck: Boolean)

    def apply(): Behavior[StartMatch] =
      Behaviors.setup { context =>
        val maxPts = 10
        val player1 = context.spawn(Player(maxPts), "player1")
        val player2 = context.spawn(Player(maxPts), "player2")
        val firstShot: Boolean = Math.random() < 0.5

        player1 ! Match.Ball("player1", "player2", player2, 0, 0, firstShot)
        Behaviors.same
      }

  }

  val matchActor = ActorSystem(Match(), "pingPongSystem")
  matchActor ! StartMatch()*/


  // ex. 3


  case class ListOfPlayers(list: List[ActorRef])

  case class Ball(counter: Int)

  class Player(name: String, id: Int) extends Actor {

    var listOfPlayers: List[ActorRef] = Nil
    val log: LoggingAdapter = Logging(context.system, self)

    override def receive: Receive = {

      case ListOfPlayers(list) => {
        listOfPlayers = list
      }


      case Ball(counter) => {
        val newCounter = counter + 1
        var throwToID = id

        while (throwToID == id) {
          val throwTo: Double = Math.random()

          throwToID = {
            if (throwTo < 1.0 / 3) 0
            else if (throwTo < 2.0 / 3) 1
            else 2
          }
        }

        log.info(s"Player ${id + 1} throws a ball with counter $newCounter to player ${throwToID + 1}")
        listOfPlayers(throwToID) ! Ball(newCounter)
      }

    }
  }

  case class StartMatch()

  case class BallMatch(system: ActorSystem) extends Actor {


    override def receive: Receive = {
      case StartMatch =>

        val player1 = system.actorOf(Props(new Player("player1", 0)), "player1")
        val player2 = system.actorOf(Props(new Player("player2", 1)), "player2")
        val player3 = system.actorOf(Props(new Player("player3", 2)), "player3")

        val listOfPlayers: List[ActorRef] = List(player1, player2, player3)

        player1 ! ListOfPlayers(listOfPlayers)
        player2 ! ListOfPlayers(listOfPlayers)
        player3 ! ListOfPlayers(listOfPlayers)


        val firstThrow: Double = Math.random()
        val firstPlayerID: Int = {
          if (firstThrow < 1.0 / 3) 0
          else if (firstThrow < 2.0 / 3) 1
          else 2
        }
        firstPlayerID match {
          case 0 => player1 ! Ball(0)
          case 1 => player2 ! Ball(0)
          case 2 => player3 ! Ball(0)
        }

      case _ => self ! PoisonPill
    }
  }

  val ballMatch = ActorSystem("ballMatchSystem")
  val startActor = ballMatch.actorOf(Props(new BallMatch(ballMatch)), "name")
  startActor ! StartMatch


  //  matchActor.terminate()
  //  welcomeActor.terminate()
  ballMatch.terminate()


}