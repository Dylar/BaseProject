package de.bornholdtlee.baseproject.kotlinexamples.delegate;

import de.bornholdtlee.baseproject.utils.Logger;

public class KotlinFunctionsImpl implements KotlinFunctions {
    @Override
    public void printStuff() {
        Logger.INSTANCE.info("Function printed stuff");
    }

    @Override
    public void doStuff() {
        Logger.INSTANCE.info("Function did stuff");
    }

    @Override
    public void doNothing() {
        //nothing
    }
}
