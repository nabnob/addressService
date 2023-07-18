package example.controller

import example.entity.Address
import example.request.AddressRequest
import example.service.AddressService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/address") // <1>
class AddressController(
    private val addressService: AddressService
) {

    @Get("/hello")// <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    fun index() = "Hello World"

    @Get("/{id}")
    fun getAddress(id: Int): Address?{
        throw Exception("Not implemented yet")
    }

    @Post
    fun createAddress(
        @Body newAddressRequest: AddressRequest
    ){
        throw Exception("Not implemented yet")
    }
}
