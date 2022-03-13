package edu.kis.powp.jobs2d.visitor;


import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImmutableCompoundCommand implements ICompoundCommand {

    private String name;

    private List<DriverCommand> commandsList;

    public Iterator<DriverCommand> iterator() {
        return commandsList.iterator();
    }

    public ImmutableCompoundCommand(List<DriverCommand> commands, String name) {
        super();
        this.name = name;
        this.commandsList = new ArrayList<>();
        commands.iterator().forEachRemaining(command -> this.commandsList.add((DriverCommand) command.clone()));
    }

    @Override
    public ImmutableCompoundCommand clone() {
        ImmutableCompoundCommand command;
        try {
            command = (ImmutableCompoundCommand) super.clone();
            command.name = this.name;
            command.commandsList = new ArrayList<>();
            for (DriverCommand cmd : this.commandsList) {
                command.commandsList.add((DriverCommand) cmd.clone());
            }
        } catch (CloneNotSupportedException e) {
            command = new ImmutableCompoundCommand(this, this.name);
        }
        return command;
    }

    public ImmutableCompoundCommand(ICompoundCommand other, String name) {
        super();
        this.name = name;
        this.commandsList = new ArrayList<>();
        other.iterator().forEachRemaining(command -> this.commandsList.add((DriverCommand) command.clone()));
    }

    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> execute(driver));
    }

}