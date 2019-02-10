package de.bornholdtlee.baseproject.utils

import android.util.Log

import de.bornholdtlee.baseproject.BuildConfig

object Logger {

    private val isAllowedToLog: Boolean
        get() = BuildConfig.DEBUG

    private val callingClass: String
        get() {
            val stackTraceElements = Thread.currentThread().stackTrace
            for (searchLoggerClassIndex in stackTraceElements.indices) {
                if (isLoggerClass(stackTraceElements[searchLoggerClassIndex])) {
                    try {
                        for (searchNextNotLoggerClassIndex in searchLoggerClassIndex until stackTraceElements.size) {
                            val callingClass = stackTraceElements[searchNextNotLoggerClassIndex]
                            if (!isLoggerClass(callingClass)) {
                                val split = callingClass.className.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                                var className = split.last()

                                if (className.contains("$")) {
                                    className = className.split("\\$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                                }
                                val isJava = callingClass.fileName.endsWith(".java")
                                return "($className${if (isJava) ".java" else ".kt"}:${callingClass.lineNumber})"
                            }
                        }
                    } catch (e: Exception) {
                        return Logger::class.java.simpleName
                    }

                }
            }
            return Logger::class.java.simpleName
        }

    private fun isLoggerClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className == Logger::class.java.canonicalName
    }

    private fun createMessage(message: String): String {
        return "<-- $message"
    }


    fun error(message: String) {
        if (isAllowedToLog) {
            Log.e(callingClass, createMessage(message))
        }
    }

    fun debug(message: String) {
        if (isAllowedToLog) {
            Log.d(callingClass, createMessage(message))
        }
    }

    fun info(message: String) {
        if (isAllowedToLog) {
            Log.i(callingClass, createMessage(message))
        }
    }

    fun verbose(message: String) {
        if (isAllowedToLog) {
            Log.v(callingClass, createMessage(message))
        }
    }

    fun warn(message: String) {
        if (isAllowedToLog) {
            Log.w(callingClass, createMessage(message))
        }
    }

    fun wtf(message: String) {
        if (isAllowedToLog) {
            Log.wtf(callingClass, createMessage(message))
        }
    }
}