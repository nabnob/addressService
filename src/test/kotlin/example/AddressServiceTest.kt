package example
import example.request.NewAddressRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import java.lang.Exception

@MicronautTest
class AddressServiceTest(
    private val application: EmbeddedApplication<*>,
    @Client("/") val client: HttpClient
) : StringSpec({

    "test the server is running" {
        assert(application.isRunning)
    }

    "test the service throws an exception when there aren't any records"{
        val request: HttpRequest<Any> = HttpRequest.GET("/address/0")  // <3>
        shouldThrow<Exception>{
            client.toBlocking().retrieve(request)
        } should{
            it.message shouldBe "Id 0 not found"
        }
    }

    "test create then get"{
        val body = NewAddressRequest("7701 Metropolis Dr")
        val request: HttpRequest<Any> = HttpRequest.POST("/address/", body)
        client.toBlocking().exchange(request, Argument.of(String::class.java)) should{
            it.status shouldBe HttpStatus.CREATED
            it.header("location") shouldBe "/address/0"
        }
    }


})
