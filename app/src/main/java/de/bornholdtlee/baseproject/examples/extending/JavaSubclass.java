package de.bornholdtlee.baseproject.examples.extending;

import org.jetbrains.annotations.NotNull;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaSubclass extends BaseClass {
    private int posX = 0;
    private int posY = 0;

    @Override
    public int getPosX() {
        return posX - 1;
    }

    @Override
    public void setPosX(int i) {
        posX = i + 1;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosY(int i) {
        posY = i;
    }

    @Override
    public void doStuff() {
        Logger.INSTANCE.info("Java stuff");
    }

    @NotNull
    @Override
    public String getName() {
        return "Java sub";
    }

    @NotNull
    @Override
    public String getType() {
        return "Java";
    }
}
