package de.bornholdtlee.baseproject.model

import de.bornholdtlee.baseproject.NULL_INTEGER
import io.objectbox.relation.ToMany

class Organizer(name: String, image: Int)
    : Person(name, image) {

    public var id: Long = NULL_INTEGER.toLong()

    lateinit var organizeLesson: ToMany<Lesson>
    lateinit var organizedLesson: ToMany<Lesson>
}