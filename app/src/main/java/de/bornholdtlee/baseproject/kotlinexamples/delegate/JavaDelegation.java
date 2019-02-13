package de.bornholdtlee.baseproject.kotlinexamples.delegate;

import de.bornholdtlee.baseproject.utils.Logger;

public class JavaDelegation extends OpenClass implements KotlinDuplicateFunctions, KotlinFunctions, JavaFunctions {

    private JavaFunctions javaFunctions = new JavaFunctionsImpl();
    private KotlinFunctions kotlinFunctions;
    private KotlinDuplicateFunctions kotlinDuplicateFunctions = new KotlinDuplicateFunctions() {
        @Override
        public void doStuff() {
            Logger.INSTANCE.info("Do anonym Stuff");
        }
    };

    public JavaDelegation(KotlinFunctions kotlinFunctions) {
        super("JavaDelegation");
        this.kotlinFunctions = kotlinFunctions;
    }

    @Override
    public void printStuff() {
        kotlinFunctions.printStuff();
    }

    @Override
    public void doStuff() {
        kotlinFunctions.doStuff();
        kotlinDuplicateFunctions.doStuff();
    }

    @Override
    public void doNothing() {
        kotlinFunctions.doNothing();
    }

    @Override
    public boolean doMoreStuff() {
        return javaFunctions.doMoreStuff();
    }
}
