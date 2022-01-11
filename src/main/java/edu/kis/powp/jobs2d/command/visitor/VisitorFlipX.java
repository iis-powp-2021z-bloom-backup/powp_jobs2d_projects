package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorFlipX implements VisitorCommand {

	@Override
	public void visit(OperateToCommand operateToCommand) {
		operateToCommand.setPosY(operateToCommand.getPosY() * (-1));
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		setPositionCommand.setPosY(setPositionCommand.getPosY() * (-1));
	}

	@Override
	public void visit(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> it = iCompoundCommand.iterator();

		while (it.hasNext()) {
			DriverCommand driverCommand = it.next();
			driverCommand.accept(this);
		}
	}
}
