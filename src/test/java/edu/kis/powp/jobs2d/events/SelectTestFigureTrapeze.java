package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.visitor.FigureForShapeSelection;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestFigureTrapeze implements ActionListener {

    private DriverManager driverManager;

    public SelectTestFigureTrapeze(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FigureForShapeSelection.trapeze(driverManager.getCurrentDriver());
    }
}
