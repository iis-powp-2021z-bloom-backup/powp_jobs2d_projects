package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

import java.awt.event.ActionListener;

public class FeaturesFeature {
    private static Application app;

    public static void setUpFeaturesManager(Application application){
        app = application;
        app.addComponentMenu(FeaturesFeature.class, "Features");
    }

    public static void addFeatureTest(String name, ActionListener actionListener){
        app.addComponentMenuElement(FeaturesFeature.class, name, actionListener);
    }
}
