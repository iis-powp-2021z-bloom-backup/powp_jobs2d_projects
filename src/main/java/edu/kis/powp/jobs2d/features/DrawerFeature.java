package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.SelectClearPanelOptionListener;
import edu.kis.legacy.drawer.panel.DrawPanelController;

import javax.swing.*;

public class DrawerFeature implements FeatureInterface {

    private static DrawPanelController drawerController;
    private static JPanel panel;
    private final Application application;

    /**
     * Setup Drawer Plugin and add to application.
     * DriverManager param not used, required by implemented interface.
     */
    public DrawerFeature(Application application){
        this.application = application;
        setup();
    }

    @Override
    public void setup() {
        panel = application.getFreePanel();
        SelectClearPanelOptionListener selectClearPanelOptionListener = new SelectClearPanelOptionListener();

        drawerController = new DrawPanelController();
        application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
        application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);

        drawerController.initialize(application.getFreePanel());
    }

    /**
     * Get controller of application drawing panel.
     *
     * @return drawPanelController.
     */
    public static DrawPanelController getDrawerController() {
        return drawerController;
    }

    public static JPanel getPanel() {
        return panel;
    }

}
