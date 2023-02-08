package ipca.lite.testpreparation

class Client {
    var id : Long? = null
    var name : String? = null
    var height : Int? = null
    var weight : Double? = null
    var age : Int? = null
    var gender : Char? = null
    var isPremium : Boolean? = null

    constructor(
        id: Long?,
        name: String?,
        height: Int?,
        weight: Double?,
        age: Int?,
        gender: Char?,
        isPremium: Boolean?
    ) {
        this.id = id
        this.name = name
        this.height = height
        this.weight = weight
        this.age = age
        this.gender = gender
        this.isPremium = isPremium
    }
}