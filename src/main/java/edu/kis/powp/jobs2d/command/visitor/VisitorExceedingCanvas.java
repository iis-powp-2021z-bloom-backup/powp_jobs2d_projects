package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorExceedingCanvas implements VisitorCommand {

    private checkPaperSize checkPaperSize;
    boolean goingBeyondDimension = false;

    public VisitorExceedingCanvas(checkPaperSize checkPaperSize) {
        this.checkPaperSize = checkPaperSize;

    }
    public void visit(SetPositionCommand setPositionCommand) {
        if (checkPaperSize.checkSize(setPositionCommand.getPosX(), setPositionCommand.getPosY())) {
            goingBeyondDimension = true;
        }
    }

    public void visit(OperateToCommand operateToCommand) {
        if ( checkPaperSize.checkSize(operateToCommand.getPosX(), operateToCommand.getPosY())) {
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