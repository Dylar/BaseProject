package de.bornholdtlee.defaultproject.utils

import android.text.format.DateFormat

import org.joda.time.DateTime

import java.util.Date

class DateUtils {

    companion object {

        val ONE_MIN_IN_MILLI = (1000 * 60).toLong()
        val FIFTEEN_MIN_IN_MILLI = ONE_MIN_IN_MILLI * 15
        val ONE_HOUR_IN_MILLI = ONE_MIN_IN_MILLI * 60
        val ONE_DAY_IN_MILLI = ONE_HOUR_IN_MILLI * 24
        val ONE_MONTH_IN_MILLI = ONE_DAY_IN_MILLI * 30
    }

    val now: DateTime
        get() = DateTime.now().withTime(0, 0, 0, 0)

    fun formatMillisToReadable(updateTimestamp: Long): String {
        return DateFormat.format("dd.MM.yyyy HH:mm", Date(updateTimestamp)).toString()
    }

    fun formatMillisToReadableOnlyYear(date: DateTime): String {
        return DateFormat.format("yyyy", date.toDate()).toString()
    }

    fun formatMillisToReadableOnlyDate(date: DateTime): String {
        return DateFormat.format("dd.MM.yyyy", date.toDate()).toString()
    }

    fun formatMillisToReadableOnlyDayMonth(date: DateTime): String {
        return DateFormat.format("dd.MM", date.toDate()).toString()
    }

    fun isSameDay(selectedDate: DateTime, date: DateTime): Boolean {
        return (date.dayOfMonth == selectedDate.dayOfMonth
                && date.monthOfYear == selectedDate.monthOfYear
                && date.year == selectedDate.year)
    }

    fun isBeforeDay(selectedDate: DateTime, date: DateTime): Boolean {
        return isBeforeMonth(selectedDate, date) || selectedDate.monthOfYear == date.monthOfYear && selectedDate.dayOfMonth < date.dayOfMonth
    }

    fun isBeforeMonth(selectedDate: DateTime, date: DateTime): Boolean {
        return selectedDate.year < date.year || selectedDate.year == date.year && selectedDate.monthOfYear < date.monthOfYear
    }

    fun getOnlyDay(dateTime: DateTime): DateTime {
        return dateTime.withDate(dateTime.year, dateTime.monthOfYear, dateTime.dayOfMonth).withTime(0, 0, 0, 0)
    }

    fun getFirstDayOfWeek(week: DateTime): DateTime {
        return week.withDayOfWeek(1).withTime(0, 0, 0, 0)
    }

    fun getLastDayOfWeek(dateTime: DateTime): DateTime {
        return dateTime.withDayOfWeek(7).withTime(0, 0, 0, 0)
    }

    fun getFirstDayOfMonth(month: DateTime): DateTime {
        return month
                .withDate(month.year, month.monthOfYear, month.dayOfMonth().withMinimumValue().dayOfMonth)
                .withTime(0, 0, 0, 0)
    }

    fun getLastDayOfMonth(month: DateTime): DateTime {
        return month
                .withDate(month.year, month.monthOfYear, month.dayOfMonth().withMaximumValue().dayOfMonth)
                .withTime(0, 0, 0, 0)
    }

    fun getFirstDayOfYear(year: DateTime): DateTime {
        return year
                .withDate(year.year, 1, 1)
                .withTime(0, 0, 0, 0)
    }

    fun getLastDayOfYear(year: DateTime): DateTime {
        return year
                .withDate(year.year, 12, 31)
                .withTime(0, 0, 0, 0)
    }

    fun normalizeDate(date: DateTime): DateTime {
        var dateTime = date
        dateTime = if (isBeforeDay(DateTime.now(), dateTime)) DateTime.now() else dateTime
        val minDate = DateTime.now().withDate(2016, 1, 1)
        dateTime = if (isBeforeDay(dateTime, minDate)) minDate else dateTime
        return dateTime
    }

    fun getDayCountBetween(start: DateTime, end: DateTime): Int {
        val endDay = if (isBeforeDay(now, end)) now.dayOfYear().get() else end.dayOfYear().get()
        return endDay - start.dayOfYear().get() + 1
    }
}
