package de.bornholdtlee.baseproject.kotlinexamples.control;

import org.jetbrains.annotations.NotNull;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaObject {

    public String getJavaObjectName() {
        return "Java Object";
    }

    public boolean isInWhen(@NotNull Object value) {
        Logger.INSTANCE.error("Value is: " + value);
        boolean isIn = true;
        if (value instanceof Double && (Double) value == 0.0) {
            Logger.INSTANCE.info("Double: " + (value instanceof Double));
        } else if (value instanceof Long && (Long) value == 0L) {
            Logger.INSTANCE.info("Long: " + (value instanceof Long));
        } else if (value instanceof Float && (Float) value == 100f) {
            Logger.INSTANCE.info("Long: " + (value instanceof Float));
        } else if (value instanceof Integer && ((Integer) value == 6 || (Integer) value == 7)) {
            Logger.INSTANCE.info(value + " is 6 oder 7");
        } else if (value instanceof Integer && (Integer) value <= 100 && (Integer) value >= 0) {
            Logger.INSTANCE.info(value + " is in Range 0 to 100");
        } else if (value instanceof Integer && ((Integer) value == 101 || (Integer) value == 103)) { //<-- Not the same
            Logger.INSTANCE.info(value + " is 101 or 103");
        } else if (value instanceof String) {
            Logger.INSTANCE.info("String: " + value);
        } else if (value instanceof JavaObject) {
            Logger.INSTANCE.info("Is JavaDelegation: " + ((JavaObject) value).getJavaObjectName());
        } else if (value instanceof KotlinAny) {
            Logger.INSTANCE.info("Is KotlinDelegation: " + ((KotlinAny) value).getKotlinObjectName());
        } else if (!(Integer.valueOf(-2).equals(value) && Integer.valueOf(-1).equals(value))) {  //<-- Not the same
            Logger.INSTANCE.info("Value is not in Range -2 to -1");
        } else {
            Logger.INSTANCE.info("only -2 or -1");
            isIn = false;
        }
        return isIn;
    }
}
