package de.bornholdtlee.baseproject.model

import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.model.converter.DateTimeConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Id
import org.joda.time.DateTime

abstract class Person(var name: String, var image:Int = 0) {

    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    lateinit var createdAt: DateTime
    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    lateinit var deletedAt: DateTime
}