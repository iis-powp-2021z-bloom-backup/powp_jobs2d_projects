package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorScale implements VisitorCommand {
	private final double scale;

	public VisitorScale(double scale) {
		this.scale = scale;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		operateToCommand.setPosX((int) (operateToCommand.getPosX() * this.scale));
		operateToCommand.setPosY((int) (operateToCommand.getPosY() * this.scale));
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		setPositionCommand.setPosX((int) (setPositionCommand.getPosX() * this.scale));
		setPositionCommand.setPosY((int) (setPositionCommand.getPosY() * this.scale));
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
