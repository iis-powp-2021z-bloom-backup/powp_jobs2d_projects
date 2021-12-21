package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CompoundCommand implements ICompoundCommand {

    private final String name;
    private final List<DriverCommand> driverCommands;

    public CompoundCommand(List<DriverCommand> commandList, String name) {
        this.name = name;
        this.driverCommands = commandList;
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
