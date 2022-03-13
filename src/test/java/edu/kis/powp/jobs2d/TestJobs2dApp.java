package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.*;
import edu.kis.powp.jobs2d.command.visitor.CheckShapeSelection;
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
		SelectTestFigureStar selectTestFigureStar = new SelectTestFigureStar(
				DriverFeature.getDriverManager());
		SelectTestFigureTrapeze selectTestFigureTrapeze = new SelectTestFigureTrapeze(
				DriverFeature.getDriverManager());
		SelectTestFigureTriangle selectTestFigureTriangle = new SelectTestFigureTriangle(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
		application.addTest("Figure Squre ShapeTest", selectTestFigureStar);
		application.addTest("Figure Trapeze ShapeTest", selectTestFigureTrapeze);
		application.addTest("Figure Triangle ShapeTest", selectTestFigureTriangle);
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

		application.addTest("Count subcommands", new SelectCommandVisitorCounterListener(DriverFeature.getDriverManager()));

		application.addTest("ICompoundCommandVisitorTest", new SelectICompoundCommandVisitorCounterListener());


		CheckShapeSelection A0 = new CheckShapeSelection(1189,841);
		CheckShapeSelection A1 = new CheckShapeSelection (841,594);
		CheckShapeSelection A2 = new CheckShapeSelection (594,420);
		CheckShapeSelection A3 = new CheckShapeSelection (420,297);
		CheckShapeSelection A4 = new CheckShapeSelection (297,210);
		CheckShapeSelection A5 = new CheckShapeSelection (210,148);
		CheckShapeSelection A6 = new CheckShapeSelection (148,105);
		CheckShapeSelection A7 = new CheckShapeSelection (105,74);
		CheckShapeSelection A8 = new CheckShapeSelection (74,52);
		CheckShapeSelection A9 = new CheckShapeSelection (52,37);
		CheckShapeSelection A10 = new CheckShapeSelection (37,26);

		application.addTest("Paper Size A0", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A0));
		application.addTest("Paper Size A1", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A1));
		application.addTest("Paper Size A2", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A2));
		application.addTest("Paper Size A3", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A3));
		application.addTest("Paper Size A4", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A4));
		application.addTest("Paper Size A5", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A5));
		application.addTest("Paper Size A6", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A6));
		application.addTest("Paper Size A7", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A7));
		application.addTest("Paper Size A8", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A8));
		application.addTest("Paper Size A9", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A9));
		application.addTest("Paper Size A10", new SelectSizeOfActionMonitor(DriverFeature.getDriverManager(),A10));


	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DriverComposite driverComposite = new DriverComposite();
		DriverFeature driverFeature = new DriverFeature(application);

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		driverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		driverFeature.addDriver("Special line Simulator", driver);

		driverComposite.add(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"));
		driverComposite.add(new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special"));
		driverFeature.addDriver("Driver composite", driverComposite);



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
				FeatureManager.addFeature(new DrawerFeature(app));
				FeatureManager.addFeature(new CommandsFeature());
				FeatureManager.addFeature(new DeviceUsageFeature());
				FeatureManager.addFeature(new DriverFeature(app));
				FeatureManager.addFeature(new RecordingFeature(app,DriverFeature.getDriverManager()));
				FeatureManager.addFeature(new CommandHistoryFeature(app));
				DriverFeature.setUpDriverNameLabelChangeManager();
				FeatureManager.setupFeatures();
				ExtensionFeature.setUpExtensionFeature(app);
				FeaturesFeature.setUpFeaturesManager(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);
				setUpExtensions();
				app.setVisibility(true);
			}
		});
	}

}
