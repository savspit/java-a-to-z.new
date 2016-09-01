package ru.shestakov.templates;

/**
 * Init TemplateAction abstract class
 */
public abstract class TemplateAction {

    /**
     * Initializes a newly created TemplateAction
     * @param name
     */
    public TemplateAction(String name) {

    }

    /**
     * Some kind of method
     */
    abstract void start();

    /**
     * Some kind of method
     */
    abstract void finish();

    /**
     * Some kind of method
     */
    public void work() {
        this.start();
        this.finish();
    }

    /**
     * Main method of TemplateAction class
     * @param args
     */
    public static void main(String[] args) {
        new TemplateAction("String") {
            public void start() {

            };
            public void finish() {

            };
        };
    }

}