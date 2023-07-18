package example.service

import example.entity.Address
import example.repository.AddressRepository
import jakarta.inject.Singleton

@Singleton
class AddressService(
    private val addressRepository: AddressRepository
) {
    fun getAddress(addressId: Int): Address?{
        return addressRepository.get(addressId)
    }
}
