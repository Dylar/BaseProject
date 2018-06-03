package de.bornholdtlee.baseproject.model

import de.bornholdtlee.baseproject.NULL_INTEGER
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Organizer(name: String) : Person(name) {

    @Id
    public var id: Long = NULL_INTEGER.toLong()

    lateinit var organizeLesson: ToMany<Lesson>
    lateinit var organizedLesson: ToMany<Lesson>
}