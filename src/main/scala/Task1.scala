import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.ActorMaterializer
import org.scalatest.{Matchers, WordSpec}

class Task1 extends WordSpec with Matchers with ScalatestRouteTest {

  import Task1.welcomeRoute

  "Task1" should {
    "return JSON with the name supplied" in {
      Get("/greet/Dhawal") ~> welcomeRoute ~> check {
        status shouldBe StatusCodes.OK
        entityAs[String] shouldBe "{\"message\": \"Hello Dhawal\"}"
      }
    }
    "return JSON of status" in {
      Get("/health") ~> welcomeRoute ~> check {
        status shouldBe StatusCodes.OK
        entityAs[String] shouldBe "{\"status\": \"OK\"}"
      }
    }
  }
}

object Task1 extends SprayJsonSupport{

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
