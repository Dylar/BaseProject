package de.bornholdtlee.defaultproject.utils;

import android.text.format.DateFormat;

import org.joda.time.DateTime;

import java.util.Date;

import de.bornholdtlee.defaultproject.Constants;
import de.bornholdtlee.snh.Constants;

public class DateUtils {
//
//    public static final long ONE_MIN_IN_MILLI = 1000 * 60;
//    public static final long FIFTEEN_MIN_IN_MILLI = ONE_MIN_IN_MILLI * 15;
//    public static final long ONE_HOUR_IN_MILLI = ONE_MIN_IN_MILLI * 60;
//    public static final long ONE_DAY_IN_MILLI = ONE_HOUR_IN_MILLI * 24;
//    public static final long ONE_MONTH_IN_MILLI = ONE_DAY_IN_MILLI * 30;
//
//    public String formatMillisToReadable(long updateTimestamp) {
//        return DateFormat.format("dd.MM.yyyy HH:mm", new Date(updateTimestamp)).toString();
//    }
//
//    public String formatMillisToReadableOnlyYear(DateTime date) {
//        return DateFormat.format("yyyy", date.toDate()).toString();
//    }
//
//    public String formatMillisToReadableOnlyMonthYear(DateTime date) {
//        return Constants.getShortMonth(date.getMonthOfYear()) + " " + formatMillisToReadableOnlyYear(date);
//    }
//
//    public String formatMillisToReadableOnlyDate(DateTime date) {
//        return DateFormat.format("dd.MM.yyyy", date.toDate()).toString();
//    }
//
//    public String formatMillisToReadableOnlyDayMonth(DateTime date) {
//        return DateFormat.format("dd.MM", date.toDate()).toString();
//    }
//
//    public boolean isSameDay(DateTime selectedDate, DateTime date) {
//        return date.getDayOfMonth() == selectedDate.getDayOfMonth()
//                && date.getMonthOfYear() == selectedDate.getMonthOfYear()
//                && date.getYear() == selectedDate.getYear();
//    }
//
//    public boolean isBeforeDay(DateTime selectedDate, DateTime date) {
//        return isBeforeMonth(selectedDate, date)
//                || selectedDate.getMonthOfYear() == date.getMonthOfYear()
//                && selectedDate.getDayOfMonth() < date.getDayOfMonth();
//    }
//
//    public boolean isBeforeMonth(DateTime selectedDate, DateTime date) {
//        return selectedDate.getYear() < date.getYear()
//                || selectedDate.getYear() == date.getYear()
//                && (selectedDate.getMonthOfYear() < date.getMonthOfYear());
//    }
//
//    public DateTime getOnlyDay(DateTime dateTime) {
//        return dateTime.withDate(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth()).withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getFirstDayOfWeek(DateTime week) {
//        return week.withDayOfWeek(1).withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getLastDayOfWeek(DateTime dateTime) {
//        return dateTime.withDayOfWeek(7).withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getFirstDayOfMonth(DateTime month) {
//        return month
//                .withDate(month.getYear(), month.getMonthOfYear(), month.dayOfMonth().withMinimumValue().getDayOfMonth())
//                .withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getLastDayOfMonth(DateTime month) {
//        return month
//                .withDate(month.getYear(), month.getMonthOfYear(), month.dayOfMonth().withMaximumValue().getDayOfMonth())
//                .withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getFirstDayOfYear(DateTime year) {
//        return year
//                .withDate(year.getYear(), 1, 1)
//                .withTime(0, 0, 0, 0);
//    }
//
//    public DateTime getLastDayOfYear(DateTime year) {
//        return year
//                .withDate(year.getYear(), 12, 31)
//                .withTime(0, 0, 0, 0);
//    }
//
//    public DateTime normalizeDate(DateTime date) {
//        DateTime dateTime = date;
//        dateTime = isBeforeDay(DateTime.now(), dateTime) ? DateTime.now() : dateTime;
//        DateTime minDate = DateTime.now().withDate(2016, 1, 1);
//        dateTime = isBeforeDay(dateTime, minDate) ? minDate : dateTime;
//        return dateTime;
//    }
//
//    public DateTime getNow() {
//        return DateTime.now().withTime(0, 0, 0, 0);
//    }
//
//    public int getDayCountBetween(DateTime start, DateTime end) {
//        int endDay = isBeforeDay(getNow(), end) ? getNow().dayOfYear().get() : end.dayOfYear().get();
//        return endDay - start.dayOfYear().get() + 1;
//    }
}
