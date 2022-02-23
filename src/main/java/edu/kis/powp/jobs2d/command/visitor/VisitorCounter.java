package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorCounter implements VisitorCommand
{
    private int counter = 0;


    public int getCounter()
    {
        return counter;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        counter++;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        counter++;
    }

    @Override
    public void visit(ICompoundCommand compoundCommand)
    {
        Iterator<DriverCommand> it = compoundCommand.iterator();

        while (it.hasNext())
        {
            DriverCommand driverCommand = it.next();
            driverCommand.accept(this);
        }
    }
}
