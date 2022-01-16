package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public abstract class TransformationVisitor implements VisitorCommand {

	abstract int calculateNewPositionX(int oldX, int oldY);
	abstract int calculateNewPositionY(int oldX, int oldY);

	@Override
	public void visit(OperateToCommand operateToCommand) {
		OperateToCommand newCommand = new OperateToCommand(
				this.calculateNewPositionX(operateToCommand.getPosX(), operateToCommand.getPosY()),
				this.calculateNewPositionY(operateToCommand.getPosX(), operateToCommand.getPosY()));
		this.replaceCommand(operateToCommand, newCommand);
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		SetPositionCommand newCommand = new SetPositionCommand(
				this.calculateNewPositionX(setPositionCommand.getPosX(), setPositionCommand.getPosY()),
				this.calculateNewPositionY(setPositionCommand.getPosX(), setPositionCommand.getPosY()));
		this.replaceCommand(setPositionCommand, newCommand);
	}

	@Override
	public void visit(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> it = iCompoundCommand.iterator();

		while (it.hasNext()) {
			DriverCommand driverCommand = it.next();
			driverCommand.accept(this);
		}
	}

	private void replaceCommand(Replaceable oldCommand, Replaceable newCommand) {
		oldCommand.replace(newCommand);
	}

}
