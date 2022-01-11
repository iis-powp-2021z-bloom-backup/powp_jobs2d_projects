package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorFlipY implements VisitorCommand {

	@Override
	public void visit(OperateToCommand operateToCommand) {
		operateToCommand.setPosX(operateToCommand.getPosX() * (-1));
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		setPositionCommand.setPosX(setPositionCommand.getPosX() * (-1));
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
