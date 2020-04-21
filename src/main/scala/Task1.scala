import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.stream.ActorMaterializer


object Task1 extends App {

  implicit val system = ActorSystem("HttpService")
  implicit val materializer = ActorMaterializer()

  import akka.http.scaladsl.server.Directives._
  import system.dispatcher

  val welcomeRoute =
    path("greet" / Segment) { (name: String) =>
      complete(
        StatusCodes.OK,
        HttpEntity(
          ContentTypes.`application/json`,
          s"""{"message": "Hello $name"}""".stripMargin
        )
      )
    } ~
    path("health") {
      complete(
        StatusCodes.OK,
        HttpEntity(
          ContentTypes.`application/json`,
          """{"status": "OK"}"""
        )
      )
    }

  Http().bindAndHandle(welcomeRoute, "0.0.0.0", 8080)

}
