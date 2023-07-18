package example.repository

import jakarta.inject.Singleton
import example.entity.Address

@Singleton
class AddressRepository {
    val map = HashMap<Int, Address>()
    var id = 0
    fun get(Id: Int): Address?{
        if(map.containsKey(Id)){
            return map.get(Id)
        }
        return null
    }
    fun create(address: Address){
        map.put(id, address)
        id++
    }

    fun update(address: Address){
        if(map.containsKey(address.id)){
            map.put(id, address)
        }else{
            throw Exception("Id ${address.id} not found")
        }
    }

    fun delete(address: Address): Address?{
        if(map.containsKey(address.id)){
            return map.remove(address.id)
        }else{
            throw Exception("Id ${address.id} not found")
        }
    }

}
