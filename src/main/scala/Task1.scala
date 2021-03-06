import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.stream.ActorMaterializer

object Task1 extends SprayJsonSupport {
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

  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(welcomeRoute, "0.0.0.0", 8080)
  }
}
