package de.bornholdtlee.defaultproject

import android.os.Environment
import java.nio.charset.Charset

class Constants {
    companion object {
        const val NULL_INTEGER = -1
        const val NO_RESULT_STRING = "-"

        val ENCODING_UTF8 = Charset.forName("UTF-8")

        val BASE_FILE_PATH = Environment.getExternalStorageDirectory().path + BuildConfig.FILE_FOLDER_NAME
        val FILE_PATH_JSONS = BASE_FILE_PATH + "jsons/"
        val FILE_PATH_IMAGES = BASE_FILE_PATH + "images/"
    }
}
