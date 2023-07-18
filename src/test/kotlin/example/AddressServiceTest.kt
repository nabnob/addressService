package example
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.micronaut.http.HttpRequest
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
        }
    }


})
