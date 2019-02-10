package de.bornholdtlee.baseproject.examples;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaObject implements TestMethodsKotlin {

    private static final String DEFAULT_IMAGE_PATH = "imagePath";
    byte[] loadedImage;
    private int width = 0;
    private int height = 0;

    public JavaObject() {
//        loadedImage = ImageUtils.INSTANCE.loadImage(DEFAULT_IMAGE_PATH, width, height);
    }

    public boolean isInWhen(Object value) {
        Logger.INSTANCE.info("Value is: " + value);
        boolean isIn;
        if (value instanceof Double && (Double) value == 0.0) {
            Logger.INSTANCE.info("Double: " + (value instanceof Double));
            isIn = true;
        } else if (value instanceof Long && (Long) value == 0L) {
            Logger.INSTANCE.info("Long: " + (value instanceof Long));
            isIn = true;
        } else if (value instanceof Float && (Float) value == 100f) {
            Logger.INSTANCE.info("Long: " + (value instanceof Float));
            isIn = true;
        } else if (value instanceof Integer && (Integer) value < 100 && (Integer) value >= 0) {
            Logger.INSTANCE.info(value + " is in Range");
            isIn = true;
        } else if (value instanceof Integer && ((Integer) value == 6 || (Integer) value == 7)) {
            Logger.INSTANCE.info(value + " is 6 oder 7");
            isIn = true;
        } else if (value instanceof String) {
            Logger.INSTANCE.info("String: " + value);
            isIn = true;
        } else isIn = false;

        return isIn;
    }
}
