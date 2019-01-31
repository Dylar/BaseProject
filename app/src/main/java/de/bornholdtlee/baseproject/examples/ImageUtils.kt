package de.bornholdtlee.baseproject.examples

import java.nio.charset.Charset

object ImageUtils {

    fun loadImage(path: String): ByteArray {
        return path.toByteArray(Charset.defaultCharset())
    }
}