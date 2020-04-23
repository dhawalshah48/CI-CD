import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class Task1Test extends WordSpec with Matchers with ScalatestRouteTest {

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
