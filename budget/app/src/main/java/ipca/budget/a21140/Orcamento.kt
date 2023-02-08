package ipca.budget.a21140

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class Orcamento {
    var descricao : String? = null
    var valor : Double? = null
    var data : Date? = null

    constructor(descricao: String?, valor: Double?, data: Date?) {
        this.descricao = descricao
        this.valor = valor
        this.data = data
    }
}