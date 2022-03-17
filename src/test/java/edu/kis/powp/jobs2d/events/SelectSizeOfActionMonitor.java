package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorExceedingCanvas;
import edu.kis.powp.jobs2d.command.visitor.CheckShapeSelection;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import static edu.kis.powp.jobs2d.features.CommandsFeature.getDriverCommandManager;

public class SelectSizeOfActionMonitor implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager ;
    private CheckShapeSelection checkShapeSelection;

    public SelectSizeOfActionMonitor(DriverManager driverManager, CheckShapeSelection checkShapeSelection) {
        this.driverManager = driverManager;
        this.checkShapeSelection = checkShapeSelection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Test of size figure on paper");

        DriverCommand command = getDriverCommandManager().getCurrentCommand();

        if(command != null){
            VisitorExceedingCanvas visitor = new VisitorExceedingCanvas(checkShapeSelection);
            command.accept(visitor);

            if(visitor.getResult())
                logger.info("Figure exceeds canvas!");
            else
                logger.info("Figure is within canvas!");

        }else {
            logger.info("Problem with SelectSizeOfActionMonitor Class!");
        }
    }
}
