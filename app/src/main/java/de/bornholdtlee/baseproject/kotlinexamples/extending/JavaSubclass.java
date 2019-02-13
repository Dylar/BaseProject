package de.bornholdtlee.baseproject.kotlinexamples.extending;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaSubclass extends BaseClass {
    private int posX = 0;
    private int posY = 0;

    public JavaSubclass() {
        super();
        init();
        methodWithDefault("Second parameter");
    }

    public JavaSubclass(String name) {
        super(name);
        init();
        methodWithDefault(name);
    }

    private void init() {
        Logger.INSTANCE.info("JavaSubclass did additional constructor stuff");
    }

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
        Logger.INSTANCE.info("Java sub stuff");
    }

    @Override
    public void openMethod() {
        super.openMethod();
        Logger.INSTANCE.info("In Java");
    }

}
