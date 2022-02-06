package edu.kis.powp.jobs2d.command.modifier;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.modifier.model.CommandType;
import edu.kis.powp.jobs2d.command.modifier.model.ModifierCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;

public class VisitorCache implements VisitorCommand {

    private final ModifierCache modifierCache;

    public VisitorCache() {
        this.modifierCache = ModifierCache.getInstance();
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        this.modifierCache.addCommand(
                new ModifierCommand(operateToCommand.getPosX(), operateToCommand.getPosY(), CommandType.OPERATE_TO)
        );
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        this.modifierCache.addCommand(
                new ModifierCommand(setPositionCommand.getPosX(), setPositionCommand.getPosY(), CommandType.SET_POSITION)
        );
    }

    @Override
    public void visit(ICompoundCommand iCompoundCommand) {
        iCompoundCommand.iterator().forEachRemaining(command ->
                command.accept(this)
        );
    }

}
