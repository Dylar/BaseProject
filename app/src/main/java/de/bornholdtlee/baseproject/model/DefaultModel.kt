package de.bornholdtlee.baseproject.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class DefaultModel {

    @Id
    var id: Long = 0

    var name: String? = null

}