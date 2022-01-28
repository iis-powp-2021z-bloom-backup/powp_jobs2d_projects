package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class ComplexCommandWindowCommandChangeObserver implements Subscriber {

    private final ComplexCommandEditorWindow complexCommandEditorWindow;

    public ComplexCommandWindowCommandChangeObserver(ComplexCommandEditorWindow complexCommandEditorWindow) {
        super();
        this.complexCommandEditorWindow = complexCommandEditorWindow;
    }

    public String toString() {
        return "Current command change observer for complex command editor window";
    }

    @Override
    public void update() {
        complexCommandEditorWindow.updateCurrentCommand();
    }

}
