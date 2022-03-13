package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorExceedingCanvas;
import edu.kis.powp.jobs2d.command.visitor.CheckShapeSelection;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectSizeOfActionMonitor implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager ;
    CheckShapeSelection checkShapeSelection;

    public SelectSizeOfActionMonitor(DriverManager driverManager, CheckShapeSelection checkShapeSelection) {
        this.driverManager = driverManager;
        this.checkShapeSelection = checkShapeSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Test of size figure on paper");

        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();

        if(command != null){
            VisitorExceedingCanvas visitor = new VisitorExceedingCanvas(checkShapeSelection);
            command.accept(visitor);

            if(visitor.getResult())
                logger.info("Figure is above paper size!");
            else
                logger.info("Figure is below paper size!");

        }else {
            logger.info("Problem with SelectSizeOfActionMonitor Class!");
        }
    }
}
