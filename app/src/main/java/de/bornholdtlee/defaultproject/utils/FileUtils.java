package de.bornholdtlee.defaultproject.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import de.bornholdtlee.defaultproject.Constants;

public class FileUtils {

    public String getJsonFromFile(Context context, String filePath) {
        String json = "";
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, Constants.ENCODING_UTF8);
            json = json.replace("\n", "");
        } catch (IOException e) {
            Logger.error("File corrupted");
        }
        return json;
    }
}
