package de.bornholdtlee.baseproject.model

import de.bornholdtlee.baseproject.NULL_INTEGER
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Attendee(name: String) : Person(name) {

    @Id
    public var id: Long = NULL_INTEGER.toLong()

    lateinit var memberLesson: ToMany<Lesson>
    lateinit var bookedLesson: ToMany<Lesson>
    lateinit var attendedLesson: ToMany<Lesson>//nachfragen ob es besucht wurde TODO
}