package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.visitor.FigureForShapeSelection;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestFigureStar implements ActionListener {

    private DriverManager driverManager;

    public SelectTestFigureStar(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FigureForShapeSelection.star(driverManager.getCurrentDriver());
    }
}
