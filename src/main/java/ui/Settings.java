package ui;

import functions.factory.TabulatedFunctionFactory;

public class Settings {

    public static Settings instance;
    private TabulatedFunctionFactory factory;

    private Settings() {
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

}
