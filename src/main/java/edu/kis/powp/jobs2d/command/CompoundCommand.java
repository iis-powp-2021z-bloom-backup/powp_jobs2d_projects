package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;


public class CompoundCommand implements ICompoundCommand {

    private String name;
    private List<DriverCommand> commandsList;

    public CompoundCommand(List<DriverCommand> commands, String name) {
        super();
        this.commandsList = new ArrayList<>();
        commands.iterator().forEachRemaining(command -> this.commandsList.add((DriverCommand) command.clone()));
        this.name = name;
    }


    public CompoundCommand(ICompoundCommand other, String name) {
        super();
        this.name = name;
        this.commandsList = new ArrayList<>();
        other.iterator().forEachRemaining(command -> this.commandsList.add((DriverCommand) command.clone()));
    }


    public List<DriverCommand> getDriverCommands() {
        return driverCommands;
    }

    @Override
    public void execute(Job2dDriver driver) {

        this.iterator().forEachRemaining(command -> command.execute(driver));
    }

    public Iterator<DriverCommand> iterator() {

        return commandsList.iterator();
    }

    @Override
    public CompoundCommand clone() {
        CompoundCommand command = null;
        try {
            command = (CompoundCommand) super.clone();
            command.name = this.name;
            command.commandsList = new ArrayList<>();
            for (DriverCommand cmd : this.commandsList) {
                command.commandsList.add((DriverCommand) cmd.clone());
            }
        } catch (CloneNotSupportedException e) {
            command = new CompoundCommand(this, this.name);
        }
        return command;
    }

    @Override
    public String toString() {
        return name;
    }

}

}

