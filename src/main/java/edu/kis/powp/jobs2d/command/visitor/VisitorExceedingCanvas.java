package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorExceedingCanvas implements VisitorCommand {

    CheckShapeSelection checkShapeSelection;
    boolean goingBeyondDimension = false;

    public VisitorExceedingCanvas(CheckShapeSelection checkShapeSelection) {
        this.checkShapeSelection = checkShapeSelection;

    }
    public void visit(SetPositionCommand setPositionCommand) {
        if (checkShapeSelection.checkPaperSize(setPositionCommand.getPosX(), setPositionCommand.getPosY())) {
            goingBeyondDimension = true;
        }
    }

    public void visit(OperateToCommand operateToCommand) {
        if ( checkShapeSelection.checkPaperSize(operateToCommand.getPosX(), operateToCommand.getPosY())) {
            goingBeyondDimension = true;
        }
    }

    public void visit(ICompoundCommand compoundCommand) {
        Iterator<DriverCommand> iterator = compoundCommand.iterator();
        while (iterator.hasNext()) {
            DriverCommand driverCommand = iterator.next();
            driverCommand.accept(this);
        }
    }

    public boolean getResult() {
        return goingBeyondDimension;
    }
}