package de.bornholdtlee.baseproject

import android.os.Environment
import java.nio.charset.Charset

const val NULL_INTEGER = -1
const val NO_RESULT_STRING = "-"

const val TAB_HOME = 0
const val TAB_MAP = 1
const val TAB_LESSON = 2

@JvmField
val ENCODING_UTF8 = Charset.forName("UTF-8")

@JvmField
val BASE_FILE_PATH = Environment.getExternalStorageDirectory().path + BuildConfig.FILE_FOLDER_NAME
@JvmField
val FILE_PATH_JSONS = BASE_FILE_PATH + "jsons/"
@JvmField
val FILE_PATH_IMAGES = BASE_FILE_PATH + "images/"
