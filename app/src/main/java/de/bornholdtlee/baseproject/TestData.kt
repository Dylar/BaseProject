package de.bornholdtlee.baseproject

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.model.Attendee
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import org.joda.time.DateTime

object TestData {

    private val hamburg = LatLng(53.565278, 10.001389)
    private val sangerhausen = LatLng(51.466667, 11.3)
    private val erfurt = LatLng(50.983333, 11.033333)
    private val kiel = LatLng(54.333333, 10.133333)
    private val koeln = LatLng(50.938056, 6.956944)
    private val wien = LatLng(48.208333, 16.373056)

    val testOrganizer = ArrayList<Organizer>()
    val testLessons = ArrayList<Lesson>()

    init {
        val peter = Organizer("Peter", R.drawable.test_organizer1)
        testOrganizer.add(peter)

        val alex = Organizer("Alex", R.drawable.test_organizer2)
        testOrganizer.add(alex)

        val hanz = Organizer("Hanz", R.drawable.test_organizer3)
        testOrganizer.add(hanz)

        val lawrence = Organizer("Lawrence", R.drawable.test_organizer4)
        testOrganizer.add(lawrence)

        val kolbenMann = Organizer("KolbenMann", R.drawable.test_organizer5)
        testOrganizer.add(kolbenMann)


        val kolbenParty = createLesson("Kolben Party", "Party mit geilen Kolben", LatLng(53.616466, 10.035964), mutableListOf(kolbenMann))
        testLessons.add(kolbenParty)

        val yoga1 = createLesson("Yoga im Park", "Yoga entspannung im Stadtpark", LatLng(53.594276, 10.021476), mutableListOf(lawrence))
        testLessons.add(yoga1)

        val yoga2 = createLesson("Yoga im Park", "Yoga entspannung im Horner Park", LatLng(53.558666, 10.085905), mutableListOf(lawrence))
        testLessons.add(yoga2)

        val spotting = createLesson("Spotting", "Spotting der Flugzeuge und so", LatLng(53.633702, 9.987739), mutableListOf(hanz))
        testLessons.add(spotting)

        val strandparty = createLesson("Strand party", "Strand chillen", LatLng(53.553808, 9.766620), mutableListOf(hanz))
        testLessons.add(strandparty)

    }

    private fun createLesson(name: String,
                             description: String = "",
                             location: LatLng,
                             organizer: MutableList<Organizer> = ArrayList(),
                             attendees: MutableList<Attendee> = ArrayList()): Lesson {
        val lesson = Lesson()
        lesson.location = location
        lesson.createdAt = DateTime.now()
        lesson.updatedAt = DateTime.now()
        lesson.name = name
        lesson.description = description
        lesson.organizer = organizer
        lesson.attendees = attendees
        return lesson

    }
}