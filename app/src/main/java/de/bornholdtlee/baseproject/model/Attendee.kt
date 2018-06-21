package de.bornholdtlee.baseproject.model

import de.bornholdtlee.baseproject.NULL_INTEGER

class Attendee(name: String) : Person(name) {

    var id: Long = NULL_INTEGER.toLong()

    lateinit var memberLesson: MutableList<Lesson>
    lateinit var bookedLesson: MutableList<Lesson>
    lateinit var attendedLesson: MutableList<Lesson>//nachfragen ob es besucht wurde TODO
}