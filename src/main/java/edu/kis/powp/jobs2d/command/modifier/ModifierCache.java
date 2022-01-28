package edu.kis.powp.jobs2d.command.modifier;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.modifier.model.CommandType;
import edu.kis.powp.jobs2d.command.modifier.model.ModifierCommand;

import java.util.ArrayList;
import java.util.List;

public class ModifierCache {

    private static volatile ModifierCache INSTANCE;

    private final List<ModifierCommand> modifierCommands;

    public static ModifierCache getInstance() {
        if (INSTANCE == null) {
            synchronized (ModifierCache.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ModifierCache();
                }
            }
        }
        return INSTANCE;
    }

    private ModifierCache() {
        this.modifierCommands = new ArrayList<>();
    }

    public CompoundCommand getCachedDriverCommands() {
        final List<DriverCommand> driverCommandList = new ArrayList<>();
        for (ModifierCommand command: modifierCommands) {
            if(command.getCommandType().equals(CommandType.OPERATE_TO)) {
                driverCommandList.add(new OperateToCommand(command.getX(), command.getY()));
            } else if (command.getCommandType().equals(CommandType.SET_POSITION)) {
                driverCommandList.add(new SetPositionCommand(command.getX(), command.getY()));
            }
        }
        return new CompoundCommand(driverCommandList, "modified command");
    }

    public List<ModifierCommand> getModifierCommands() {
        return modifierCommands;
    }

    public void addCommand(ModifierCommand modifierCommand) {
        this.modifierCommands.add(modifierCommand);
    }

    public void clearCache() {
        this.modifierCommands.clear();
    }

}
