package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.*;
import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.DeviceUsageDecorator;
import edu.kis.powp.jobs2d.drivers.SelectMouseFigureOptionListener;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.*;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
	}

	/**
	 * Setup test using driver commands in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		FeaturesFeature.addFeatureTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		FeaturesFeature.addFeatureTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

		FeaturesFeature.addFeatureTest("Mouse figure", new SelectMouseFigureOptionListener(application.getFreePanel(), DriverFeature.getDriverManager()));

		FeaturesFeature.addFeatureTest("Count subcommands", new SelectCommandVisitorCounterListener(DriverFeature.getDriverManager()));
		FeaturesFeature.addFeatureTest("ICompoundCommandVisitorTest", new SelectICompoundCommandVisitorCounterListener());

	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);

		//driverComposite.add(new LoggerDriver());
		DriverComposite driverComposite = new DriverComposite();
		driverComposite.add(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"));
		driverComposite.add(new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special"));
		DriverFeature.addDriver("Driver composite", driverComposite);

	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);

		DeviceUsageCalculatorWindow deviceUsageCalculatorWindow = new DeviceUsageCalculatorWindow(DeviceUsageFeature.getDeviceUsageManager());
		application.addWindowComponent("Device Usage Manager", deviceUsageCalculatorWindow);

		DeviceUsageCalculatorWindowDistanceChangeObserver deviceUsageWindowObserver =
				new DeviceUsageCalculatorWindowDistanceChangeObserver(deviceUsageCalculatorWindow);
		DeviceUsageFeature.getDeviceUsageManager().getPublisher().addSubscriber(deviceUsageWindowObserver);

		ComplexCommandEditorWindow complexCommandEditorWindow = new ComplexCommandEditorWindow();
		ComplexCommandWindowCommandChangeObserver complexCommandWindowCommandChangeObserver = new ComplexCommandWindowCommandChangeObserver(complexCommandEditorWindow);
		application.addWindowComponent("Complex command editor", complexCommandEditorWindow);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(complexCommandWindowCommandChangeObserver);

		TransformationMangerWindow transformationManger = new TransformationMangerWindow();
		application.addWindowComponent("Transformation manager", transformationManger);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	private static void setUpExtensions(){
		Job2dDriver driver = new LoggerDriver();
		ExtensionFeature.addExtensionDriver("Logger driver", driver);

		Job2dDriver driverDeviceUsage = new DeviceUsageDecorator(DriverFeature.getDriverManager(), DeviceUsageFeature.getDeviceUsageManager());
		ExtensionFeature.addExtensionDriver("Driver with device usage",driverDeviceUsage);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app,app.getFreePanel());
				CommandsFeature.setupCommandManager();
				DeviceUsageFeature.setupDeviceUsageManager();
				DriverFeature.setupDriverPlugin(app);
				DriverFeature.setUpDriverNameLabelChangeManager();
				ExtensionFeature.setUpExtensionFeature(app);
				FeaturesFeature.setUpFeaturesManager(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);
				setUpExtensions();
				RecordingFeature.setupRecordingPlugin(app, DriverFeature.getDriverManager());
				app.setVisibility(true);
			}
		});
	}

}
