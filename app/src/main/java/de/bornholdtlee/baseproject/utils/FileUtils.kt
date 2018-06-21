package de.bornholdtlee.baseproject.utils

import android.content.Context

import java.io.IOException
import java.io.InputStream

import de.bornholdtlee.baseproject.ENCODING_UTF8

class FileUtils {

    fun getJsonFromFile(context: Context, filePath: String): String {
        var json = ""
        try {
            val inputStream = context.assets.open(filePath)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, ENCODING_UTF8)
            json = json.replace("\n", "")
        } catch (e: IOException) {
            Logger.error("File corrupted")
        }

        return json
    }
}
