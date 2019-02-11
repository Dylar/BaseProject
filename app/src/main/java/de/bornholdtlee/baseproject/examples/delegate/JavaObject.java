package de.bornholdtlee.baseproject.examples.delegate;

public class JavaObject implements KotlinFunctions, JavaFunctions {

    private JavaFunctions javaFunctions = new JavaFunctionsImpl();
    private KotlinFunctions kotlinFunctions = new KotlinFunctionsImpl();

    @Override
    public void printStuff() {
        kotlinFunctions.printStuff();
    }

    @Override
    public void doStuff() {
        kotlinFunctions.doStuff();
    }

    @Override
    public void doNothing() {
        kotlinFunctions.doNothing();
    }

    @Override
    public void moreStuff() {
        javaFunctions.moreStuff();
    }

    @Override
    public boolean doMoreStuff() {
        return javaFunctions.doMoreStuff();
    }
}
