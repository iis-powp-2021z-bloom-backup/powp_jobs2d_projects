package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class VisitorRotate implements VisitorCommand{
	private final double cos;
	private final double sin;

	public VisitorRotate(double angle) {
		this.cos = Math.cos(Math.toRadians(angle));
		this.sin = Math.sin(Math.toRadians(angle));
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		int x = operateToCommand.getPosX();
		int y = operateToCommand.getPosY();
		operateToCommand.setPosX(this.calculateNewPositionX(x, y));
		operateToCommand.setPosY(this.calculateNewPositionY(x, y));
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		int x = setPositionCommand.getPosX();
		int y = setPositionCommand.getPosY();
		setPositionCommand.setPosX(this.calculateNewPositionX(x, y));
		setPositionCommand.setPosY(this.calculateNewPositionY(x, y));
	}

	@Override
	public void visit(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> it = iCompoundCommand.iterator();

		while (it.hasNext())
		{
			DriverCommand driverCommand = it.next();
			driverCommand.accept(this);
		}
	}

	private int calculateNewPositionX(int x, int y) {
		return (int) ((x*this.cos) - (y*this.sin));
	}

	private int calculateNewPositionY(int x, int y) {
		return (int) ((x*this.sin) + (y*this.cos));
	}
}
