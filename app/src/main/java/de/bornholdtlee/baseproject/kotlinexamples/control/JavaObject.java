package de.bornholdtlee.baseproject.kotlinexamples.control;

import org.jetbrains.annotations.NotNull;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaObject {

    public String getJavaObjectName() {
        return "Java Object";
    }

    public boolean isInWhen(@NotNull Object value) {
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
        } else if (value instanceof JavaObject) {
            Logger.INSTANCE.info("Is JavaObject: " + ((JavaObject) value).getJavaObjectName());
            isIn = true;
        } else if (value instanceof KotlinAny) {
            Logger.INSTANCE.info("Is KotlinAny: " + ((KotlinAny) value).getKotlinObjectName());
            isIn = true;
        } else isIn = false;

        return isIn;
    }
}
