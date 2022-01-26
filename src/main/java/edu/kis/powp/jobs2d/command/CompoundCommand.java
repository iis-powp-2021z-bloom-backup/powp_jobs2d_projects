package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.modifier.model.OperationNotSupportedException;
import edu.kis.powp.jobs2d.command.modifier.model.Point;
import edu.kis.powp.jobs2d.command.modifier.IVisitorModifier;

import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CompoundCommand implements ICompoundCommand {

    private final String name;
    private final List<DriverCommand> driverCommands;

    public CompoundCommand(List<DriverCommand> commandList, String name) {
        this.name = name;
        this.driverCommands = commandList;
    }

    public List<DriverCommand> getDriverCommands() {
        return driverCommands;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommands.forEach((c) -> c.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommands.iterator();
    }

    @Override
    public Point getPoint(IVisitorModifier visitor) {
        throw new OperationNotSupportedException();
    }

    @Override
    public void setPoint(IVisitorModifier visitor, Point point) {
        throw new OperationNotSupportedException();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object clone() {
        return new CompoundCommand(driverCommands.stream()
                .map(x -> (DriverCommand) x.clone())
                .collect(toList()), this.name);
    }

}
