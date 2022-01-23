package edu.kis.powp.jobs2d.features;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.ExtensionsManager;
import edu.kis.powp.jobs2d.drivers.SelectExtensionOptionListener;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExtensionFeature {
	private static ExtensionsManager extensionsManager = new ExtensionsManager();
	private static Application app;


	/**
	 * Setup Extension Plugin and add to application.
	 *
	 * @param application Application context.
	 */
	public static void setUpExtensionFeature(Application application){
		app = application;
		app.addComponentMenu(ExtensionFeature.class,"Extensions");
	}
	/**
	 * Add extensions to context, create button in extension menu.
	 *
	 * @param name   Button name.
	 * @param driver Job2dDriver object.
	 */
	public static void addExtension(String name, Job2dDriver driver){
		SelectExtensionOptionListener listener = new SelectExtensionOptionListener();
		app.addComponentMenuElementWithCheckBox(ExtensionFeature.class, name, listener,false);
		addExtensionToList(driver);
	}

	private static void addExtensionToList(Job2dDriver job2dDriver){
		extensionsManager.addExtension(job2dDriver);
	}
}
