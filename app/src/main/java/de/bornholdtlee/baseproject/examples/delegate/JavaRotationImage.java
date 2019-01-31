package de.bornholdtlee.baseproject.examples.delegate;

import android.graphics.Point;

import org.jetbrains.annotations.NotNull;

public class JavaRotationImage implements KotlinRotationInterface {

    @NotNull
    @Override
    public Point getRotationPoint() {
        return new Point(0,0);
    }


}
