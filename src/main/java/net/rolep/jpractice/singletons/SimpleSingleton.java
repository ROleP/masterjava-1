package net.rolep.jpractice.singletons;

/**
 * Created by rolep on 3/15/17.
 */
public class SimpleSingleton {

    private static volatile SimpleSingleton instance;

    private SimpleSingleton() {}

    public static SimpleSingleton getInstance() {

/*
        if (instance==null) {
            synchronized (SimpleSingleton.class) {
                if (instance==null) {
                    instance = new SimpleSingleton();
                }
            }
        }

        return instance;*/

        return SimpleSingletonHolder.INSTANCE;
    }

    private static class SimpleSingletonHolder {
        private static final SimpleSingleton INSTANCE = new SimpleSingleton();
    }
}
